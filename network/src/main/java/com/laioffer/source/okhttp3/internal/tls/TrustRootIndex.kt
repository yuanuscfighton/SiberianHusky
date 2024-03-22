package com.laioffer.source.okhttp3.internal.tls

import java.security.cert.X509Certificate

interface TrustRootIndex {
  /** Returns the trusted CA certificate that signed [cert]. */
  fun findByIssuerAndSignature(cert: X509Certificate): X509Certificate?
}
