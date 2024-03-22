package com.laioffer.source.okhttp3.internal.connection

import java.io.IOException
import com.laioffer.source.okhttp3.Interceptor
import com.laioffer.source.okhttp3.Response
import com.laioffer.source.okhttp3.internal.http.RealInterceptorChain

/**
 * Opens a connection to the target server and proceeds to the next interceptor. The network might
 * be used for the returned response, or to validate a cached response with a conditional GET.
 */
object ConnectInterceptor : Interceptor {
  @Throws(IOException::class)
  override fun intercept(chain: Interceptor.Chain): Response {
    val realChain = chain as RealInterceptorChain
    // 获取连接 Exchange：数据交换（封装了连接）
    val exchange = realChain.call.initExchange(chain)
    val connectedChain = realChain.copy(exchange = exchange)
    return connectedChain.proceed(realChain.request)
  }
}
