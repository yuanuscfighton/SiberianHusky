/** Exposes Kotlin-internal APIs to Java test code and code in other modules. */
@file:JvmName("Internal")

package com.laioffer.source.okhttp3.internal

import okhttp3.Cache
import com.laioffer.source.okhttp3.ConnectionSpec
import com.laioffer.source.okhttp3.Cookie
import com.laioffer.source.okhttp3.Headers
import com.laioffer.source.okhttp3.HttpUrl
import com.laioffer.source.okhttp3.Request
import javax.net.ssl.SSLSocket

fun parseCookie(currentTimeMillis: Long, url: HttpUrl, setCookie: String): Cookie? =
    Cookie.parse(currentTimeMillis, url, setCookie)

fun cookieToString(cookie: Cookie, forObsoleteRfc2965: Boolean) =
    cookie.toString(forObsoleteRfc2965)

fun addHeaderLenient(builder: Headers.Builder, line: String) =
    builder.addLenient(line)

fun addHeaderLenient(builder: Headers.Builder, name: String, value: String) =
    builder.addLenient(name, value)

fun cacheGet(cache: Cache, request: Request) = cache.get(request)

fun applyConnectionSpec(connectionSpec: ConnectionSpec, sslSocket: SSLSocket, isFallback: Boolean) =
    connectionSpec.apply(sslSocket, isFallback)
