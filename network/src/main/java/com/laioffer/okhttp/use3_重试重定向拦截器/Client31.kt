package com.laioffer.okhttp.use3_重试重定向拦截器

import okhttp3.Authenticator
import okhttp3.OkHttpClient

/**
 * 类的描述: 自定义ProxyAuthenticator拦截器
 * Created by 春夏秋冬在中南 on 2023/9/3 21:43
 */
fun main() {
  val okHttpClient = OkHttpClient.Builder()
    .proxyAuthenticator(object : Authenticator {
      override fun authenticate(
        route: com.laioffer.source.okhttp3.Route?,
        response: com.laioffer.source.okhttp3.Response
      ): com.laioffer.source.okhttp3.Request? {
        return response.request
          .newBuilder()
          /*
             重定向条件：
                通过proxyAuthenticator获得到了Request。例如，添加Proxy-Authorization请求头
             说明：
                响应码是407，表示「代理」需要鉴权
                i.e. 代理需要授权，例如，付费代理，需要验证身份
           */
          .addHeader("Proxy-Authorization", "xxx")
          .build()
      }
    })
}