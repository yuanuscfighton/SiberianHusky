package Kotlin基础.pkg3_构造方法

/**
 * @description 方法1:使用init初始化成员变量
 * @date 2023/1/29 14:42
 */
class Person1 constructor(username: String){

  // 使用init初始化
  private var username: String
  private var age: Int
  private var address: String

  init {
    this.username = username
    age = 20
    address = "beijing"
  }
}
