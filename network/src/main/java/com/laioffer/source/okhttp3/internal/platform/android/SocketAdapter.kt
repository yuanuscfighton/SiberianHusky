package com.laioffer.source.okhttp3.internal.platform.android

import com.laioffer.source.okhttp3.Protocol
import javax.net.ssl.SSLSocket
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

interface SocketAdapter {
  fun isSupported(): Boolean
  fun trustManager(sslSocketFactory: SSLSocketFactory): X509TrustManager? = null
  fun matchesSocket(sslSocket: SSLSocket): Boolean
  fun matchesSocketFactory(sslSocketFactory: SSLSocketFactory): Boolean = false

  fun configureTlsExtensions(
    sslSocket: SSLSocket,
    hostname: String?,
    protocols: List<Protocol>
  )

  fun getSelectedProtocol(sslSocket: SSLSocket): String?
}
