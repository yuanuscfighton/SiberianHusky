package com.laioffer.source.okhttp3.internal.tls

import com.laioffer.source.okhttp3.internal.platform.Platform
import java.security.cert.Certificate
import java.security.cert.X509Certificate
import javax.net.ssl.SSLPeerUnverifiedException
import javax.net.ssl.X509TrustManager

/**
 * Computes the effective certificate chain from the raw array returned by Java's built in TLS APIs.
 * Cleaning a chain returns a list of certificates where the first element is `chain[0]`, each
 * certificate is signed by the certificate that follows, and the last certificate is a trusted CA
 * certificate.
 *
 * Use of the chain cleaner is necessary to omit unexpected certificates that aren't relevant to
 * the TLS handshake and to extract the trusted CA certificate for the benefit of certificate
 * pinning.
 */
abstract class CertificateChainCleaner {

  @Throws(SSLPeerUnverifiedException::class)
  abstract fun clean(chain: List<Certificate>, hostname: String): List<Certificate>

  companion object {
    fun get(trustManager: X509TrustManager): CertificateChainCleaner {
      return Platform.get().buildCertificateChainCleaner(trustManager)
    }

    fun get(vararg caCerts: X509Certificate): CertificateChainCleaner {
      return BasicCertificateChainCleaner(BasicTrustRootIndex(*caCerts))
    }
  }
}
