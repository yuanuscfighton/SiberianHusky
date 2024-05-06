package Kotlin学习.pkg18_匿名函数域闭包

// 匿名函数

fun main() {

  fun(x: Int, y: Int) = x + y

  fun(x: Int, y: Int): Int {
    return x + y
  }

  val content = arrayOf("hello", "world", "react", "welcome", "bye")

  content.filter(fun(item): Boolean = item.length > 3)
    .forEach(fun(item) { println(item) })

}
