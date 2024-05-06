package Kotlin基础.pkg15_对象表达式

// Kotlin的对象表达式主要是为了替代Java中的匿名内部类。
// 类似于Java的匿名内部类，Kotlin对象表达式中的代码是可以访问到外层的变量。
// 与Java不同的是，外层变量无需声明为final。

fun main() {
  var i = 100

  val myObject = object {
    fun foo() {
      i++
    }
  }

  myObject.foo()
  println(i)
}
