package Kotlin基础.pkg15_对象表达式

class MyClass2 {
  private fun method1() = object {
    val str = "hello"
  }

  internal fun method2() = object {
    val str = "world"
  }

  fun test() {
    val s = method1().str
    // val s1 = method2().str // error ❌
  }
}
