package Kotlin基础.pkg10_扩展

/**
 * 伴生对象的属性扩展
 */
class CompanionObjectExtension {
  companion object MyObject {
    fun foo() {
      println("hello")
    }
  }
}

fun CompanionObjectExtension.MyObject.method() {
  println("hello 张三")
}

fun main() {
  CompanionObjectExtension.method()
  CompanionObjectExtension.foo()
}
