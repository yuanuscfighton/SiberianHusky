package Kotlin基础.pkg3_构造方法

/**
 * @description 在创建对象的时候，就给成员变量赋上初值
 * @date 2023/1/29 14:54
 */
class Student(private val username: String, private val age: Int, private var address: String) {
  // 这里定义的username，age，address就是类的成员变量(或者叫作，类的属性)。
  // 定义在了类的构造方法参数上了
  // 这里一步到位的 声明了属性，且在对象创建的时候，把参数值赋给属性了


  fun printInfo() {
    println("username=${username}, age=${age}, address=${address}")
  }
}

fun main() {
  val student = Student("张三", 10, "北京")
  student.printInfo()
}
