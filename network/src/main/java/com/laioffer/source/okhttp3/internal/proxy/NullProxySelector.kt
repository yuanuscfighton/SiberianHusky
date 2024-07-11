package com.laioffer.source.okhttp3.internal.proxy

import java.io.IOException
import java.net.Proxy
import java.net.ProxySelector
import java.net.SocketAddress
import java.net.URI

/**
 * A proxy selector that always returns the [Proxy.NO_PROXY].
 */
object NullProxySelector : ProxySelector() {
  override fun select(uri: URI?): List<Proxy> {
    requireNotNull(uri) { "uri must not be null" }
    return listOf(Proxy.NO_PROXY)
  }

  override fun connectFailed(uri: URI?, sa: SocketAddress?, ioe: IOException?) {
  }
}
