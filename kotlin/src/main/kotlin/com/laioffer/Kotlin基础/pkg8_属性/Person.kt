package Kotlin基础.pkg8_属性

class Person {
  // 定义一个属性(property)
  val age: Int = 20
}

fun main() {

  val person = Person()

  // 表面上是直接用Person的实例访问age属性，但实际上是调用了age的getter方法
  println(person.age)
}
