package Kotlin基础.pkg5_接口

interface A1 {
  fun method() {
    println("A1")
  }
}

open class B1 {
  open fun method() {
    println("B1")
  }
}

// ❌错误代码
//class C1 : A1,B1() { // 让C1实现接口A1，且继承类B1
//
//}
// 错误信息:Class 'C1' must override public open fun method(): Unit defined in A1,
// because it inherits many implementations of it.
