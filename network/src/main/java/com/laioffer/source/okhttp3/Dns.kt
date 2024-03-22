package com.laioffer.source.okhttp3

import java.net.InetAddress
import java.net.UnknownHostException
import com.laioffer.source.okhttp3.Dns.Companion.SYSTEM

/**
 * A domain name service that resolves IP addresses for host names. Most applications will use the
 * [system DNS service][SYSTEM], which is the default. Some applications may provide their own
 * implementation to use a different DNS server, to prefer IPv6 addresses, to prefer IPv4 addresses,
 * or to force a specific known IP address.
 *
 * Implementations of this interface must be safe for concurrent use.
 */
interface Dns {
  /**
   * Returns the IP addresses of `hostname`, in the order they will be attempted by OkHttp. If a
   * connection to an address fails, OkHttp will retry the connection with the next address until
   * either a connection is made, the set of IP addresses is exhausted, or a limit is exceeded.
   */
  @Throws(UnknownHostException::class)
  fun lookup(hostname: String): List<InetAddress>

  companion object {
    /**
     * A DNS that uses [InetAddress.getAllByName] to ask the underlying operating system to
     * lookup IP addresses. Most custom [Dns] implementations should delegate to this instance.
     */
    @JvmField
    val SYSTEM: Dns = DnsSystem()
    private class DnsSystem : Dns {
      override fun lookup(hostname: String): List<InetAddress> {
        try {
          return InetAddress.getAllByName(hostname).toList()
        } catch (e: NullPointerException) {
          throw UnknownHostException("Broken system behaviour for dns lookup of $hostname").apply {
            initCause(e)
          }
        }
      }
    }
  }
}
