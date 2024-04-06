package Kotlin基础.pkg10_扩展

/**
 * - 类里的函数优先级比同名的扩展函数优先级更高一些
 * - 扩展支持重载
 */
class CC {

  fun foo() {
    println("member")
  }

  fun fn() {
    println("fn method")
  }
}

// 扩展的函数名和CC类中的一样
fun CC.foo() {
  println("member1")
}

fun CC.fn(i: Int) {
  println("fn method!!!")
}

fun main() {
  val cc = CC()
  cc.foo()

  cc.fn(1)
}
