package Kotlin学习.pkg17_lambda表达式

fun myCalculate(a: Int, b: Int, calculate: (Int, Int) -> Int): Int {
  return calculate(a, b)
}

fun main() {
  println(myCalculate(2, 3) { x, y ->
    x + y
  })
}
