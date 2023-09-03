package com.laioffer.okhttp.use2

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

/**
 * 类的描述: 自定义拦截器
 * Created by 春夏秋冬在中南 on 2023/9/3 10:56
 */
fun main() {
  val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(object : Interceptor {
      override fun intercept(chain: Interceptor.Chain): Response {
        /*
          自定义拦截器:
            处理Request：添加signature参数
         */
        val request = chain.request()
        val url = request.url.newBuilder()
          .addQueryParameter("signature", "xxx")
          .build()

        val newRequest = request.newBuilder().url(url).build()

        /* 处理Response */
        val newResponse = chain.proceed(newRequest)
        // newResponse.xxx
        println(newResponse)
        return newResponse
      }
    })

  /*
      httpLoggingInterceptor：日志拦截器
        (1) 如果加到「ApplicationInterceptor」中，只能打印用户直接发出的请求
        (2) 如果添加到「NetworkInterceptor」中，打印出的是内容是经过了OkHttp各种拦截器后 真正发出去的请求，
              比如桥接连接器会补全请求头数据
   */
}
