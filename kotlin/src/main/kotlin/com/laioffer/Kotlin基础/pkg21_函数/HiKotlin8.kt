package Kotlin基础.pkg21_函数

/**
 * 带接收者的函数字面值
 * Kotlin提供了一个功能：可以通过指定的接收者对象调用一个函数字面值。
 * 在函数字面值内部，可以调用接收者对象的方法，而不需使用任何额外的修饰符。
 *
 * 这一点非常类似扩展函数
 */

fun main() {
  // (other: Int) -> Int = { other -> this - other } 是函数的字面值
  // 接收者类型是Int. 谁调用是就是接收者
  // 函数本身是有一个参数other，类型是Int，并返回一个Int值
  // 函数体是 { other -> this - other }
  val subtract: Int.(other: Int) -> Int = { other -> this - other }

  println(1.subtract(3))

  /**
   * 匿名函数语法可以让我们指定函数字面值的接收者类型。这样，我们就可以先去声明一个带有接收者的函数类型变量，然后再去使用它
   */
  val sum = fun Int.(other: Int): Int = this + other

  println(1.sum(2))

  /**
   * 带有接收者类型的函数的非字面值可以作为参数进行传递，前提是所需要接收函数的地方应该有一个接收者类型的参数
   * e.g. 类型 String.(Int) -> Boolean 与 (String, Int) -> Boolean 等价
   */
  val myEquals: String.(Int) -> Boolean = { param -> this.toIntOrNull() == param }
  println("456".myEquals(456))

  fun myTest(op: (String, Int) -> Boolean, a: String, b: Int, c: Boolean) = println(op(a, b) == c)
  myTest(myEquals, "200", 200, true)
}
