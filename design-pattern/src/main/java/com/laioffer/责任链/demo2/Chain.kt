package com.laioffer.责任链.demo2

/**
 * 类的描述: 链条
 * Created by 春夏秋冬在中南 on 2023/9/2 22:50
 */
class Chain {

  private lateinit var interceptors: List<Interceptor>

  // Request被第几个拦截器处理
  private var index: Int = 0
  var request: String = ""

  constructor(interceptors: List<Interceptor>, index: Int, request: String) {
    this.interceptors = interceptors
    this.index = index
    this.request = request
  }

  constructor(interceptors: List<Interceptor>, index: Int) {
    this.interceptors = interceptors
    this.index = index
  }

  /**
   * 接收并处理请求
   */
  fun proceed(request: String): String {
    if (index >= interceptors.size) {
      throw AssertionError()
    }

    // 创建新的链条对象
    val chain = Chain(
      interceptors = interceptors,
      index = index + 1,
      request = request
    )
    val interceptor = interceptors[index]
    return interceptor.intercept(chain)
  }
}