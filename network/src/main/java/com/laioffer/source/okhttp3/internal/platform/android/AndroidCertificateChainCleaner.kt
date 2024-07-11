package com.laioffer.source.okhttp3.internal.platform.android

import android.net.http.X509TrustManagerExtensions
import com.laioffer.source.okhttp3.internal.SuppressSignatureCheck
import com.laioffer.source.okhttp3.internal.tls.CertificateChainCleaner
import java.security.cert.Certificate
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLPeerUnverifiedException
import javax.net.ssl.X509TrustManager

/**
 * Android implementation of CertificateChainCleaner using direct Android API calls.
 * Not used if X509TrustManager doesn't implement [X509TrustManager.checkServerTrusted] with
 * an additional host param.
 */
internal class AndroidCertificateChainCleaner(
  private val trustManager: X509TrustManager,
  private val x509TrustManagerExtensions: X509TrustManagerExtensions
) : CertificateChainCleaner() {
  @Suppress("UNCHECKED_CAST")
  @Throws(SSLPeerUnverifiedException::class)
  @SuppressSignatureCheck
  override
  fun clean(chain: List<Certificate>, hostname: String): List<Certificate> {
    val certificates = (chain as List<X509Certificate>).toTypedArray()
    try {
      return x509TrustManagerExtensions.checkServerTrusted(certificates, "RSA", hostname)
    } catch (ce: CertificateException) {
      throw SSLPeerUnverifiedException(ce.message).apply { initCause(ce) }
    }
  }

  override fun equals(other: Any?): Boolean =
      other is AndroidCertificateChainCleaner &&
          other.trustManager === this.trustManager

  override fun hashCode(): Int = System.identityHashCode(trustManager)

  companion object {
    @SuppressSignatureCheck
    fun buildIfSupported(trustManager: X509TrustManager): AndroidCertificateChainCleaner? {
      val extensions = try {
        X509TrustManagerExtensions(trustManager)
      } catch (iae: IllegalArgumentException) {
        // X509TrustManagerExtensions checks for checkServerTrusted(X509Certificate[], String, String)
        null
      }

      return when {
        extensions != null -> AndroidCertificateChainCleaner(trustManager, extensions)
        else -> null
      }
    }
  }
}
