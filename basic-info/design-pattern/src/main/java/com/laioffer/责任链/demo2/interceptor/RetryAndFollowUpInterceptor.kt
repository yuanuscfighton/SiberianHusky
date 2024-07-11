package com.laioffer.责任链.demo2.interceptor

import com.laioffer.责任链.demo2.Chain
import com.laioffer.责任链.demo2.Interceptor

/**
 * 类的描述:重试定向拦截器
 * Created by 春夏秋冬在中南 on 2023/9/2 23:05
 */
class RetryAndFollowUpInterceptor : Interceptor {

  /* 该方法中的chain对象是 新创建的链条对象，即(index+1)的链条对象 */
  override fun intercept(chain: Chain): String {
    /* 可以在执行下一个拦截器之前，做自己的事情*/
    println("开始执行 重试定向拦截器")

    /*
      执行下一个拦截器
      「chain.request + "==> 经过重试定向拦截器"」是"RetryAndFollowUpInterceptor"拦截器处理后的请求对象，
          交给chain对象(i.e. index+1的对象)继续处理
     */
    val result = chain.proceed(chain.request + " → 重试定向拦截器")

    /* 获取结果后，加一些自己的东西 */
    println("结束执行重试定向拦截器")

    return "$result 👉🏻 重试定向拦击器"
  }
}