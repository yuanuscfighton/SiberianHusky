package Kotlin学习.pkg18_匿名函数域闭包

// 闭包
// Lambda表达式或者匿名函数，可以访问到在其作用域外层声明的变量。
// 对于Java来说，如果想在Lambda表达式里面访问其外层的变量，那么这个变量是不可以被修改的(即，是final的)。
// 而对于Kotlin来说，在闭包中所捕获的变量是可以被修改的

fun main() {
  var sum = ""

  val content = arrayOf("hello", "world", "bye")

  content.filter { it.length > 3 }.forEach { sum += it }

  println(sum)
}
