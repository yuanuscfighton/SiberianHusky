package Kotlin学习.pkg17_lambda表达式

// 第3个参数的名字是compute，其类型是(x: Int, y: Int) -> Unit
// Lambda表达式含义是输入两个参数x和y，返回值是Unit类型(类似void)
fun foo(a: Int = 1, b: Int = 2, compute: (x: Int, y: Int) -> Unit) {
  compute(a, b)
}
// 具体计算什么，是由compute传进来的Lambda表达式决定的

fun bar(a: Int, b: Int) = println(a - b)

fun main() {
  foo(2, 3, ::bar)
  ////////////////////////////////////////////////
  ////////////////////////////////////////////////

  foo(2, 3, { x, y -> println(x - y) })

  // 参数a和b不能有括号，
  // Type mismatch.
  // Required: (Int, Int) → Unit
  // Found: (Int) → Unit
  // foo(2, 3, {(a, b) -> println(a - b)})

  ////////////////////////////////////////////////
  ////////////////////////////////////////////////

  // 1.如果方法最后一个参数是Lambda表达式，那么这个Lambda表达式
  // 既可以按照参数的形式放在小括号当中，
  // 也可以放到所谓的方法执行体中
  foo(2, 3) { x, y ->
    println(x - y)
  }

  ////////////////////////////////////////////////
  ////////////////////////////////////////////////

  // 2.如果方法最后一个参数是Lambda表达式，而且Lambda表达式写在了小括号之外，
  // 那么就可以不指定Lambda表达式的具体参数名

  // 使用b的默认值
  foo(2) { x, y ->
    println(x - y)
  }

  // 都使用默认值
  foo { x, y -> println(x - y) }
}
