package Kotlin基础.pkg21_函数

// 具名函数
// 在调用函数的时候，函数参数可以是具名的。
// 当一个函数有大量参数或者一些参数拥有默认值的时候，这种调用方式是比较方便的。

fun test2(a: Int, b: Int = 2, c: Int = 3, d: Int) = println(a + b + c + d)

fun main() {
  test2(1, 2,3 ,4)

  test2(a = 1, d = 5)
}
