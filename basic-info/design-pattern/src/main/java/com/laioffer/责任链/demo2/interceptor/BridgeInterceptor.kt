package com.laioffer.责任链.demo2.interceptor

import com.laioffer.责任链.demo2.Chain
import com.laioffer.责任链.demo2.Interceptor

/**
 * 类的描述:桥接拦截器
 * Created by 春夏秋冬在中南 on 2023/9/2 23:04
 */
class BridgeInterceptor :Interceptor {

  override fun intercept(chain: Chain): String {
    println("开始执行 桥接拦截器")

    val result = chain.proceed(chain.request + " → 桥接拦截器")

    println("结束执行桥接拦截器")

    return "$result 👉🏻 经过桥接拦击器"
  }
}