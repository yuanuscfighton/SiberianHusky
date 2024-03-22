package com.laioffer.source.okhttp3

/**
 * Versions of TLS that can be offered when negotiating a secure socket. See
 * [javax.net.ssl.SSLSocket.setEnabledProtocols].
 */
enum class TlsVersion(
  @get:JvmName("javaName") val javaName: String
) {
  TLS_1_3("TLSv1.3"), // 2016.
  TLS_1_2("TLSv1.2"), // 2008.
  TLS_1_1("TLSv1.1"), // 2006.
  TLS_1_0("TLSv1"), // 1999.
  SSL_3_0("SSLv3"); // 1996.

  @JvmName("-deprecated_javaName")
  @Deprecated(
      message = "moved to val",
      replaceWith = ReplaceWith(expression = "javaName"),
      level = DeprecationLevel.ERROR)
  fun javaName() = javaName

  companion object {
    @JvmStatic
    fun forJavaName(javaName: String): TlsVersion {
      return when (javaName) {
        "TLSv1.3" -> TLS_1_3
        "TLSv1.2" -> TLS_1_2
        "TLSv1.1" -> TLS_1_1
        "TLSv1" -> TLS_1_0
        "SSLv3" -> SSL_3_0
        else -> throw IllegalArgumentException("Unexpected TLS version: $javaName")
      }
    }
  }
}
