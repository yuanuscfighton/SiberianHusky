package com.laioffer.责任链.demo2.interceptor

import com.laioffer.责任链.demo2.Chain
import com.laioffer.责任链.demo2.Interceptor

/**
 * 类的描述: 连接拦截器
 * Created by 春夏秋冬在中南 on 2023/9/2 23:05
 */
class ConnectInterceptor : Interceptor {

  override fun intercept(chain: Chain): String {
    println("开始执行 连接拦截器")

    val result = chain.proceed(chain.request + " → 连接拦截器")

    println("结束执行连接拦截器")

    return "$result 👉🏻 经过连接拦击器"
  }
}