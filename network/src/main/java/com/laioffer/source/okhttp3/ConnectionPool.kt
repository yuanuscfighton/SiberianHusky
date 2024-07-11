package com.laioffer.source.okhttp3

import java.util.concurrent.TimeUnit
import com.laioffer.source.okhttp3.internal.concurrent.TaskRunner
import com.laioffer.source.okhttp3.internal.connection.RealConnectionPool

/**
 * Manages reuse of HTTP and HTTP/2 connections for reduced network latency. HTTP requests that
 * share the same [Address] may share a [Connection]. This class implements the policy
 * of which connections to keep open for future use.
 *
 * @constructor Create a new connection pool with tuning parameters appropriate for a single-user
 * application. The tuning parameters in this pool are subject to change in future OkHttp releases.
 * Currently this pool holds up to 5 idle connections which will be evicted after 5 minutes of
 * inactivity.
 */
class ConnectionPool internal constructor(
  internal val delegate: RealConnectionPool
) {
  constructor(
    maxIdleConnections: Int,
    keepAliveDuration: Long,
    timeUnit: TimeUnit
  ) : this(
    RealConnectionPool(
      taskRunner = TaskRunner.INSTANCE,
      maxIdleConnections = maxIdleConnections,
      keepAliveDuration = keepAliveDuration,
      timeUnit = timeUnit
  )
  )

  constructor() : this(5, 5, TimeUnit.MINUTES)

  /** Returns the number of idle connections in the pool. */
  fun idleConnectionCount(): Int = delegate.idleConnectionCount()

  /** Returns total number of connections in the pool. */
  fun connectionCount(): Int = delegate.connectionCount()

  /** Close and remove all idle connections in the pool. */
  fun evictAll() {
    delegate.evictAll()
  }
}
