package com.laioffer.source.okhttp3.internal.connection

import com.laioffer.source.okhttp3.internal.concurrent.Task
import com.laioffer.source.okhttp3.internal.concurrent.TaskQueue
import com.laioffer.source.okhttp3.internal.concurrent.TaskRunner
import okhttp3.Address
import com.laioffer.source.okhttp3.ConnectionPool
import com.laioffer.source.okhttp3.Route
import com.laioffer.source.okhttp3.internal.assertThreadHoldsLock
import com.laioffer.source.okhttp3.internal.closeQuietly
import com.laioffer.source.okhttp3.internal.connection.RealCall.CallReference
import com.laioffer.source.okhttp3.internal.okHttpName
import com.laioffer.source.okhttp3.internal.platform.Platform
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.TimeUnit

//连接对象复用池
class RealConnectionPool(
  taskRunner: TaskRunner,
  /** The maximum number of idle connections for each address. */
  private val maxIdleConnections: Int,
  keepAliveDuration: Long,
  timeUnit: TimeUnit
) {
  private val keepAliveDurationNs: Long = timeUnit.toNanos(keepAliveDuration)

  private val cleanupQueue: TaskQueue = taskRunner.newQueue()
  private val cleanupTask = object : Task("$okHttpName ConnectionPool") {
    override fun runOnce() = cleanup(System.nanoTime())
  }

  /**
   * Holding the lock of the connection being added or removed when mutating this, and check its
   * [RealConnection.noNewExchanges] property. This defends against races where a connection is
   * simultaneously adopted and removed.
   */
  private val connections = ConcurrentLinkedQueue<RealConnection>()

  init {
    // Put a floor on the keep alive duration, otherwise cleanup will spin loop.
    require(keepAliveDuration > 0L) { "keepAliveDuration <= 0: $keepAliveDuration" }
  }

  fun idleConnectionCount(): Int {
    return connections.count {
      synchronized(it) { it.calls.isEmpty() }
    }
  }

  fun connectionCount(): Int {
    return connections.size
  }

  /**
   * Attempts to acquire a recycled connection to [address] for [call]. Returns true if a connection
   * was acquired.
   *
   * If [routes] is non-null these are the resolved routes (ie. IP addresses) for the connection.
   * This is used to coalesce related domains to the same HTTP/2 connection, such as `square.com`
   * and `square.ca`.
   */
  fun callAcquirePooledConnection(
    address: Address,
    call: RealCall,
    routes: List<Route>?,
    requireMultiplexed: Boolean
  ): Boolean {
    for (connection in connections) {
      synchronized(connection) {
        if (requireMultiplexed && !connection.isMultiplexed) return@synchronized
        if (!connection.isEligible(address, routes)) return@synchronized
        call.acquireConnectionNoEvents(connection)
        return true
      }
    }
    return false
  }

  fun put(connection: RealConnection) {
    connection.assertThreadHoldsLock()

    connections.add(connection)
    cleanupQueue.schedule(cleanupTask)
  }

  /**
   * Notify this pool that [connection] has become idle. Returns true if the connection has been
   * removed from the pool and should be closed.
   */
  fun connectionBecameIdle(connection: RealConnection): Boolean {
    connection.assertThreadHoldsLock()

    return if (connection.noNewExchanges || maxIdleConnections == 0) {
      connection.noNewExchanges = true
      connections.remove(connection)
      if (connections.isEmpty()) cleanupQueue.cancelAll()
      true
    } else {
      cleanupQueue.schedule(cleanupTask)
      false
    }
  }

  fun evictAll() {
    val i = connections.iterator()
    while (i.hasNext()) {
      val connection = i.next()
      val socketToClose = synchronized(connection) {
        if (connection.calls.isEmpty()) {
          i.remove()
          connection.noNewExchanges = true
          return@synchronized connection.socket()
        } else {
          return@synchronized null
        }
      }
      socketToClose?.closeQuietly()
    }

    if (connections.isEmpty()) cleanupQueue.cancelAll()
  }

  /**
   * Performs maintenance on this pool, evicting the connection that has been idle the longest if
   * either it has exceeded the keep alive limit or the idle connections limit.
   *
   * Returns the duration in nanoseconds to sleep until the next scheduled call to this method.
   * Returns -1 if no further cleanups are required.
   */
  fun `cleanup`(now: Long): Long {
    var inUseConnectionCount = 0
    var idleConnectionCount = 0
    var longestIdleConnection: RealConnection? = null
    var longestIdleDurationNs = Long.MIN_VALUE

    // Find either a connection to evict, or the time that the next eviction is due.
    for (connection in connections) {
      synchronized(connection) {
        // If the connection is in use, keep searching.
        // 记录正在使用与已经闲置的连接数
        if (pruneAndGetAllocationCount(connection, now) > 0) {
          inUseConnectionCount++
        } else {
          idleConnectionCount++

          // 记录最长闲置时间的连接longestIdleConnection
          // If the connection is ready to be evicted, we're done.
          val idleDurationNs = now - connection.idleAtNs
          if (idleDurationNs > longestIdleDurationNs) {
            longestIdleDurationNs = idleDurationNs
            longestIdleConnection = connection
          } else {
            Unit
          }
        }
      }
    }

    when {
      //最长闲置时间的连接超过了允许闲置时间 或者 闲置数量超过允许数量，清理此连接
      longestIdleDurationNs >= this.keepAliveDurationNs
          || idleConnectionCount > this.maxIdleConnections -> {
        // We've chosen a connection to evict. Confirm it's still okay to be evict, then close it.
        val connection = longestIdleConnection!!
        synchronized(connection) {
          if (connection.calls.isNotEmpty()) return 0L // No longer idle.
          if (connection.idleAtNs + longestIdleDurationNs != now) return 0L // No longer oldest.
          connection.noNewExchanges = true
          connections.remove(longestIdleConnection)
        }

        connection.socket().closeQuietly()
        if (connections.isEmpty()) cleanupQueue.cancelAll()

        // Clean up again immediately.
        return 0L
      }
      //存在闲置连接，下次执行清理任务在 允许闲置时间-已经闲置时候后
      idleConnectionCount > 0 -> {
        // A connection will be ready to evict soon.
        return keepAliveDurationNs - longestIdleDurationNs
      }
      //存在使用中的连接，下次清理在 允许闲置时间后
      inUseConnectionCount > 0 -> {
        // All connections are in use. It'll be at least the keep alive duration 'til we run
        // again.
        return keepAliveDurationNs
      }

      else -> {
        // No connections, idle or in use.
        return -1
      }
    }
  }

  /**
   * Prunes any leaked calls and then returns the number of remaining live calls on [connection].
   * Calls are leaked if the connection is tracking them but the application code has abandoned
   * them. Leak detection is imprecise and relies on garbage collection.
   */
  private fun pruneAndGetAllocationCount(connection: RealConnection, now: Long): Int {
    connection.assertThreadHoldsLock()

    val references = connection.calls
    var i = 0
    while (i < references.size) {
      val reference = references[i]

      if (reference.get() != null) {
        i++
        continue
      }

      // We've discovered a leaked call. This is an application bug.
      val callReference = reference as CallReference
      val message = "A connection to ${connection.route().address.url} was leaked. " +
          "Did you forget to close a response body?"
      Platform.get().logCloseableLeak(message, callReference.callStackTrace)

      references.removeAt(i)
      connection.noNewExchanges = true

      // If this was the last allocation, the connection is eligible for immediate eviction.
      if (references.isEmpty()) {
        connection.idleAtNs = now - keepAliveDurationNs
        return 0
      }
    }

    return references.size
  }

  companion object {
    fun get(connectionPool: ConnectionPool): RealConnectionPool = connectionPool.delegate
  }
}