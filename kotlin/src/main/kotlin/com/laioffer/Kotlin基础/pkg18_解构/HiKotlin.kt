package Kotlin基础.pkg18_解构

data class MyResult(val result: String, val status: Int)

fun myMethod(): MyResult {
  return MyResult("Success", 1)
}

fun main() {
  val (result, status) = myMethod()
  println(result)
  println(status)
}
