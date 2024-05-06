package Kotlin基础.pkg21_函数

// 对于重写的方法来说，子类所拥有的重写方法会使用与父类相同的默认参数值。
// 在重写一个拥有默认参数值的方法时，方法签名中必须要将默认参数值省略掉。

open class A {
  open fun foo(a: Int, b: Int = 2) = a + b
}

class B: A() {
  // 这里的参数b使用的是基类的默认值2，
  // 重写的方法中不能指定b的默认值
  // error_msg=An overriding function is not allowed to specify default values for its parameters
  override fun foo(a: Int, b: Int) = a + b
}

fun main() {
  println(A().foo(5))

  println(B().foo(2))
}
