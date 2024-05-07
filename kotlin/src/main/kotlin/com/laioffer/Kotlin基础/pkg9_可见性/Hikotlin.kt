package Kotlin基础.pkg9_可见性

// Kotlin提供了4种可见性修饰符：private, protected, internal, public

// 在kotlin中，什么都不写，默认是public
fun method() {} // <-- 顶层函数，i.e. 直接在包下写的

class Clazz // <-- 顶层类

private fun method1() {} // <-- 顶层函数，被private修饰，只能在该文件中可见

internal fun method2() {} // <-- 被internal修饰，只能在同一个模块中可见
// 模块: 最终编译后，被编译到一起的文件
// e.g. 我们使用gradle构建该工程，那么整个项目称之为一个模块

// 报错❌
// protected fun method3() {} // <-- Modifier 'protected' is not applicable to 'top level function

open class Clazz1 {

  private val b = 3 // <-- 只能在当前类中使用

  protected open val c = 4 // <-- 当前类及其子类可以使用

  internal val d = 5
}

class SubClazz : Clazz1() {
  fun foo() {
    print("c=$c")
  }
}
