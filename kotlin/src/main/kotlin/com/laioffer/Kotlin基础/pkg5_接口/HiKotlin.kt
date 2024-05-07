package com.laioffer.Kotlin基础.pkg5_接口

interface A {
  // Kotlin 的接口，可以只有声明，没有实现
  fun method()
}

interface B {
  // 和 Java8 类似，方法前无需有 default 修饰符，Kotlin 的接口中，可以有具体的实现
  fun method1() {
    println("B")
  }
}

// B后面不能有小括号，因为接口是不能实例化的
class C : B {
  override fun method1() {
    println("C")
  }
}

fun main() {
  val c = C()
  c.method1()
}
