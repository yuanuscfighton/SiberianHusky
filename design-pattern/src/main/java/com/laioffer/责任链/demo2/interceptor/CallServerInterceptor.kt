package com.laioffer.责任链.demo2.interceptor

import com.laioffer.责任链.demo2.Chain
import com.laioffer.责任链.demo2.Interceptor

/**
 * 类的描述:请求服务器拦截器
 * Created by 春夏秋冬在中南 on 2023/9/2 23:05
 */
class CallServerInterceptor : Interceptor {

  override fun intercept(chain: Chain): String {
    println("开始执行 请求服务器拦截器")

    println("发起请求")

    println("结束执行请求服务器拦截器")

    /* 由于是最后一个拦截器，直接return */

    return chain.request + " → 请求服务器拦截器\nHttp响应"
  }
}