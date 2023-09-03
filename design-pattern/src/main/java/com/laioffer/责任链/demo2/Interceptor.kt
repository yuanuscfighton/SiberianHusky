package com.laioffer.责任链.demo2

/**
 * 类的描述:拦截器接口
 * Created by 春夏秋冬在中南 on 2023/9/2 22:47
 */
interface Interceptor {

 fun intercept(chain: Chain): String
}

/*
  每个要处理请求的对象，都需要实现该接口
 */