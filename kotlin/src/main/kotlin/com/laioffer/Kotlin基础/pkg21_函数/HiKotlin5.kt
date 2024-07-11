package Kotlin基础.pkg21_函数

// 一个方法中，只允许一个参数为vararg，通常作为最后一个参数。
// 如果vararg不是最后一个参数，那么其后的参数就需要通过具名
// 参数形式进行传递；如果其后的参数是函数类型，那么还可以通过
// 在圆括号外传递lambda表达式来实现。

fun <T> convert2List(vararg elements: T): List<T> {
  val result = ArrayList<T>()
  elements.forEach { result.add(it) }
  return result
}

fun main() {
  println(convert2List("hello", "world", "hello world"))
}
