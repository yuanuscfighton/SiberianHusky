package Kotlin基础.pkg21_函数

// 默认参数(default arguments)
fun foo(a: Int = 0, b: Int = 1) = println(a - b)

fun main() {
  foo()

  foo(2)

  // 显示指定参数名
  foo(b = 2)

  foo(5, 2)
}
