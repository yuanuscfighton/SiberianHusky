package com.laioffer.Kotlin基础.pkg3_构造方法

/**
 * Kotlin构造方法详解
 */
class Person constructor(username: String) {

  // 1.成员变量未初始化
  // 错误写法:
  //  private var username: String
  //  private var age: Int
  //  private var address: String
  // ❌错误信息 Property must be initialized or be abstract

  private var username = "abc"


}
