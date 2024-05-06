package Kotlin学习.pkg17_lambda表达式

// Lambda表达式格式要求：
// 1. 一个lambda表达式总是被一对花括号所包围。
// 2. 其参数（如果有的话）位于 -> 之前（参数类型是可以省略掉的）
// 3. 执行体位于 -> 之后
// 在Kotlin中，如果一个函数的最后一个参数是个函数，那么可以将lambda表达式作为实参传递进去，并且可以在调用时，放在方法圆括号外去使用

// Lambda表达式放在大括号里面
// { a, b -> a * b }
// (Int, Int) -> Int是multiply类型
// multiply是函数类型
val multiply: (Int, Int) -> Int = { a: Int, b: Int -> a * b }
val add: (Int, Int) -> Int = { a, b -> a + b }

// 省略了类型
val subtract = { a: Int, b: Int -> a - b }

// 没有参数，返回值是Unit类型
val myAction: () -> Unit = { println("hi") }

// 省略写法
val myAction1 = { println("hello world") }

// 返回值可能是null
val mayReturnNull: (Int, Int) -> Int? = { _, _ -> null }

// 函数可能是null
val functionMayNull: ((Int, Int) -> Int)? = null
