package Kotlin基础.pkg5_接口

interface A2 {
  fun method2() {
    println("A2")
  }
}

open class B2 {
  open fun method2() {
    println("B2")
  }
}

// fix HiKotlin1中的问题
class C2 : A2, B2() {
  override fun method2() {
    // <A2> 表示类C2会调用接口A2中的method方法
    super<A2>.method2()

    super<B2>.method2()
  }
}

fun main() {
  val c2 = C2()
  c2.method2()
}
