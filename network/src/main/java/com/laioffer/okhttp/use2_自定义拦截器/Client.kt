package com.laioffer.okhttp.use2_自定义拦截器

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

/**
 * 类的描述:自定义拦截器
 * Created by 春夏秋冬在中南 on 2023/9/3 10:38
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
        /* 向链条中传入新的Request对象 */
        return chain.proceed(newRequest)
      }
    })
}