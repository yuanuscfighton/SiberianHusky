package Kotlin基础.pkg6_抽象类

open class BaseClass {
  open fun method() {
  }
}

abstract class ChildClass : BaseClass() {

  abstract override fun method()
}
