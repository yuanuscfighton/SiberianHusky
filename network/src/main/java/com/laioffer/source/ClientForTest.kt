package com.laioffer.source

import com.laioffer.source.okhttp3.OkHttpClient
import com.laioffer.source.okhttp3.Request
import com.laioffer.source.okhttp3.Response
import com.laioffer.source.okhttp3.Route
import okhttp3.Authenticator
import java.net.InetSocketAddress
import java.net.Proxy

class ClientForTest {

  val okHttpClient = OkHttpClient.Builder()
    .proxy(Proxy(Proxy.Type.HTTP, InetSocketAddress(66)))
    .proxyAuthenticator(object : Authenticator {
      override fun authenticate(route: Route?, response: Response): Request? {
        return response.request.newBuilder()
          .addHeader("Proxy-Authorization", "xxxx")
          .build()
      }

    })

    .build()
}