package Kotlin基础.pkg21_函数

// 字符串类型的可变参数
fun foo(vararg integers: Int) {
  println(integers.javaClass)
  integers.forEach { println(it) }

  // strings.forEach({ it -> println(it)})
}


fun main() {
  foo(integers = intArrayOf(1, 2, 3, 4))

  // 可变参数可以借助spread operator以具名参数的形式传递
  val arrays = intArrayOf(1, 2, 3, 4)
  foo(*arrays)
}
