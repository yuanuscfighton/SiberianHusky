package com.laioffer.Kotlin基础.pkg4_继承与重写

open class MyParent {
  // val name: String = "parent"
  open val name: String = "parent"

  open var job: String = "job"
}

class MyChild : MyParent() {
  // ❌ 'name' in 'MyParent' is final and cannot be overridden
  override val name = "child"

  // ❌ Val-property cannot override var-property
  // （1）val 可以 override val
  // （2）var 可以 override val
  // （3）val 不能 override var
  // 本质上 val 相当于 get 方法，var 相当于 set 和 get 方法
  // override val job = "child job"
}

class MyChild1(override val name: String) : MyParent() {

}

fun main() {
  val child = MyChild()
  println(child.name)
}