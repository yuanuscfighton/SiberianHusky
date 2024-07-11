package com.laioffer.source.okhttp3.internal.platform.android

import com.laioffer.source.okhttp3.Protocol
import com.laioffer.source.okhttp3.internal.platform.AndroidPlatform
import com.laioffer.source.okhttp3.internal.platform.Platform
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.nio.charset.StandardCharsets
import javax.net.ssl.SSLSocket

/**
 * Modern reflection based SocketAdapter for Conscrypt class SSLSockets.
 *
 * This is used directly for providers where class name is known e.g. the Google Play Provider
 * but we can't compile directly against it, or in fact reliably know if it is registered and
 * on classpath.
 */
open class AndroidSocketAdapter(private val sslSocketClass: Class<in SSLSocket>) : SocketAdapter {
  private val setUseSessionTickets: Method =
    sslSocketClass.getDeclaredMethod("setUseSessionTickets", Boolean::class.javaPrimitiveType)
  private val setHostname = sslSocketClass.getMethod("setHostname", String::class.java)
  private val getAlpnSelectedProtocol = sslSocketClass.getMethod("getAlpnSelectedProtocol")
  private val setAlpnProtocols =
    sslSocketClass.getMethod("setAlpnProtocols", ByteArray::class.java)

  override fun isSupported(): Boolean = AndroidPlatform.isSupported

  override fun matchesSocket(sslSocket: SSLSocket): Boolean = sslSocketClass.isInstance(sslSocket)

  override fun configureTlsExtensions(
    sslSocket: SSLSocket,
    hostname: String?,
    protocols: List<Protocol>
  ) {
    // No TLS extensions if the socket class is custom.
    if (matchesSocket(sslSocket)) {
      try {
        // Enable session tickets.
        setUseSessionTickets.invoke(sslSocket, true)

        if (hostname != null) {
          // This is SSLParameters.setServerNames() in API 24+.
          setHostname.invoke(sslSocket, hostname)
        }

        // Enable ALPN.
        setAlpnProtocols.invoke(
            sslSocket,
            Platform.concatLengthPrefixed(protocols)
        )
      } catch (e: IllegalAccessException) {
        throw AssertionError(e)
      } catch (e: InvocationTargetException) {
        throw AssertionError(e)
      }
    }
  }

  override fun getSelectedProtocol(sslSocket: SSLSocket): String? {
    // No TLS extensions if the socket class is custom.
    if (!matchesSocket(sslSocket)) {
      return null
    }

    return try {
      val alpnResult = getAlpnSelectedProtocol.invoke(sslSocket) as ByteArray?
      if (alpnResult != null) String(alpnResult, StandardCharsets.UTF_8) else null
    } catch (e: NullPointerException) {
      when {
        // https://github.com/square/okhttp/issues/5587
        e.message == "ssl == null" -> null
        else -> throw e
      }
    } catch (e: IllegalAccessException) {
      throw AssertionError(e)
    } catch (e: InvocationTargetException) {
      throw AssertionError(e)
    }
  }

  companion object {
    val playProviderFactory: DeferredSocketAdapter.Factory =
      factory("com.google.android.gms.org.conscrypt")

    /**
     * Builds a SocketAdapter from an observed implementation class, by grabbing the Class
     * reference to perform reflection on at runtime.
     *
     * @param actualSSLSocketClass the runtime class of Conscrypt class socket.
     */
    private fun build(actualSSLSocketClass: Class<in SSLSocket>): AndroidSocketAdapter {
      var possibleClass: Class<in SSLSocket>? = actualSSLSocketClass
      while (possibleClass != null && possibleClass.simpleName != "OpenSSLSocketImpl") {
        possibleClass = possibleClass.superclass

        if (possibleClass == null) {
          throw AssertionError(
              "No OpenSSLSocketImpl superclass of socket of type $actualSSLSocketClass"
          )
        }
      }

      return AndroidSocketAdapter(possibleClass!!)
    }

    fun factory(packageName: String): DeferredSocketAdapter.Factory {
      return object : DeferredSocketAdapter.Factory {
        override fun matchesSocket(sslSocket: SSLSocket): Boolean =
          sslSocket.javaClass.name.startsWith("$packageName.")

        override fun create(sslSocket: SSLSocket): SocketAdapter {
          return build(sslSocket.javaClass)
        }
      }
    }
  }
}
