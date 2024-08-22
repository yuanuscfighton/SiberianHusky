package com.laioffer.Kotlin基础.pkg3_构造方法

/**
 * Kotlin构造方法详解
 *
 * 方法1:使用init初始化成员变量
 */
class Person1 constructor(username: String) {

  // 使用init初始化
  private var username: String
  private var age: Int
  private var address: String

  // 构造方法的初始化放在 init 中
  init {
    println(username) // 这里的 username 是构造方法中的 username

    this.username = username
    age = 20
    address = "beijing"
  }
}
