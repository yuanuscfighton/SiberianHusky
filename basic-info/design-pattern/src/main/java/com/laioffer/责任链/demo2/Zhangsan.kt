package com.laioffer.责任链.demo2

import com.laioffer.责任链.demo2.interceptor.BridgeInterceptor
import com.laioffer.责任链.demo2.interceptor.CacheInterceptor
import com.laioffer.责任链.demo2.interceptor.CallServerInterceptor
import com.laioffer.责任链.demo2.interceptor.ConnectInterceptor
import com.laioffer.责任链.demo2.interceptor.RetryAndFollowUpInterceptor

/**
 * 类的描述: 程序入口
 * Created by 春夏秋冬在中南 on 2023/9/3 10:10
 */
fun main() {

 val interceptors = ArrayList<Interceptor>()
 interceptors.add(RetryAndFollowUpInterceptor())
 interceptors.add(BridgeInterceptor())
 interceptors.add(CacheInterceptor())
 interceptors.add(ConnectInterceptor())
 interceptors.add(CallServerInterceptor())

 val chain = Chain(interceptors, 0)
 println(chain.proceed("Http请求"))
}