package com.laioffer.source.okhttp3.internal.platform

import android.annotation.SuppressLint
import android.os.Build
import android.security.NetworkSecurityPolicy
import javax.net.ssl.SSLSocket
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager
import com.laioffer.source.okhttp3.Protocol
import com.laioffer.source.okhttp3.internal.SuppressSignatureCheck
import com.laioffer.source.okhttp3.internal.platform.android.Android10SocketAdapter
import com.laioffer.source.okhttp3.internal.platform.android.AndroidCertificateChainCleaner
import com.laioffer.source.okhttp3.internal.platform.android.AndroidSocketAdapter
import com.laioffer.source.okhttp3.internal.platform.android.DeferredSocketAdapter
import com.laioffer.source.okhttp3.internal.tls.CertificateChainCleaner

/** Android 29+. */
@SuppressSignatureCheck
class Android10Platform : Platform() {
  private val socketAdapters = listOfNotNull(
      Android10SocketAdapter.buildIfSupported(),
      DeferredSocketAdapter(AndroidSocketAdapter.playProviderFactory),
      // Delay and Defer any initialisation of Conscrypt and BouncyCastle
  ).filter { it.isSupported() }

  override fun trustManager(sslSocketFactory: SSLSocketFactory): X509TrustManager? =
      socketAdapters.find { it.matchesSocketFactory(sslSocketFactory) }
          ?.trustManager(sslSocketFactory)

  override fun configureTlsExtensions(sslSocket: SSLSocket, hostname: String?, protocols: List<Protocol>) {
    // No TLS extensions if the socket class is custom.
    socketAdapters.find { it.matchesSocket(sslSocket) }
        ?.configureTlsExtensions(sslSocket, hostname, protocols)
  }

  override fun getSelectedProtocol(sslSocket: SSLSocket) =
      // No TLS extensions if the socket class is custom.
      socketAdapters.find { it.matchesSocket(sslSocket) }?.getSelectedProtocol(sslSocket)

  @SuppressLint("NewApi")
  override fun isCleartextTrafficPermitted(hostname: String): Boolean =
      NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(hostname)

  override fun buildCertificateChainCleaner(trustManager: X509TrustManager): CertificateChainCleaner =
      AndroidCertificateChainCleaner.buildIfSupported(trustManager) ?: super.buildCertificateChainCleaner(trustManager)

  companion object {
    val isSupported: Boolean = isAndroid && Build.VERSION.SDK_INT >= 29

    fun buildIfSupported(): Platform? = if (isSupported) Android10Platform() else null
  }
}
