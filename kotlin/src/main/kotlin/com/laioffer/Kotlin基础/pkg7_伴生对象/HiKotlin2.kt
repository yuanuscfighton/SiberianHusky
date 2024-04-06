package Kotlin基础.pkg7_伴生对象

class HiKotlin2 {

  // 伴生对象可以没有显式名字
  // 如果不提供伴生对象的名字，那么编译器会提供一个默认的名字 Companion
  companion object {

    var a: Int = 100

    fun method() {
      println("method invoked")
    }
  }
}

fun main() {

  HiKotlin2.Companion.method()

  println("----------")

  HiKotlin2.method()
}
