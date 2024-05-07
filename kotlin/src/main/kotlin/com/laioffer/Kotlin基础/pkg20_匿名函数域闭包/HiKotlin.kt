package Kotlin学习.pkg18_匿名函数域闭包

fun main() {
  val content = arrayOf("hello", "world", "react", "welcome", "bye")

  content.filter { it.contains("h") }
    .forEach { println(it) }

  println("----------")

  content.filter { s -> s.length > 4 }
    .forEach { println(it) }

}
