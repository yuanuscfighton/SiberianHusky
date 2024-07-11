package com.laioffer.source.okhttp3.internal.http

import java.net.HttpURLConnection
import java.net.Proxy
import com.laioffer.source.okhttp3.HttpUrl
import com.laioffer.source.okhttp3.Request

object RequestLine {

  /**
   * Returns the request status line, like "GET / HTTP/1.1". This is exposed to the application by
   * [HttpURLConnection.getHeaderFields], so it needs to be set even if the transport is
   * HTTP/2.
   */
  fun get(request: Request, proxyType: Proxy.Type) = buildString {
    append(request.method)
    append(' ')
    if (includeAuthorityInRequestLine(request, proxyType)) {
      append(request.url)
    } else {
      append(requestPath(request.url))
    }
    append(" HTTP/1.1")
  }

  /**
   * Returns true if the request line should contain the full URL with host and port (like "GET
   * http://android.com/foo HTTP/1.1") or only the path (like "GET /foo HTTP/1.1").
   */
  private fun includeAuthorityInRequestLine(request: Request, proxyType: Proxy.Type): Boolean {
    return !request.isHttps && proxyType == Proxy.Type.HTTP
  }

  /**
   * Returns the path to request, like the '/' in 'GET / HTTP/1.1'. Never empty, even if the request
   * URL is. Includes the query component if it exists.
   */
  fun requestPath(url: HttpUrl): String {
    val path = url.encodedPath
    val query = url.encodedQuery
    return if (query != null) "$path?$query" else path
  }
}
