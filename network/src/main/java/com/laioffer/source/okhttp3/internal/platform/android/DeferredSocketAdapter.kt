package com.laioffer.source.okhttp3.internal.platform.android

import com.laioffer.source.okhttp3.Protocol
import javax.net.ssl.SSLSocket

/**
 * Deferred implementation of SocketAdapter that works by observing the socket
 * and initializing on first use.
 *
 * We use this because eager classpath checks cause confusion and excessive logging in Android,
 * and we can't rely on classnames after proguard, so are probably best served by falling through
 * to a situation of trying our least likely noisiest options.
 */
class DeferredSocketAdapter(private val socketAdapterFactory: Factory) : SocketAdapter {
  private var delegate: SocketAdapter? = null

  override fun isSupported(): Boolean {
    return true
  }

  override fun matchesSocket(sslSocket: SSLSocket): Boolean =
    socketAdapterFactory.matchesSocket(sslSocket)

  override fun configureTlsExtensions(
    sslSocket: SSLSocket,
    hostname: String?,
    protocols: List<Protocol>
  ) {
    getDelegate(sslSocket)?.configureTlsExtensions(sslSocket, hostname, protocols)
  }

  override fun getSelectedProtocol(sslSocket: SSLSocket): String? {
    return getDelegate(sslSocket)?.getSelectedProtocol(sslSocket)
  }

  @Synchronized private fun getDelegate(sslSocket: SSLSocket): SocketAdapter? {
    if (this.delegate == null && socketAdapterFactory.matchesSocket(sslSocket)) {
      this.delegate = socketAdapterFactory.create(sslSocket)
    }

    return delegate
  }

  interface Factory {
    fun matchesSocket(sslSocket: SSLSocket): Boolean
    fun create(sslSocket: SSLSocket): SocketAdapter
  }
}
