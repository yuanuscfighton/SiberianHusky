package com.laioffer.Kotlin基础.pkg4_继承与重写

open class Fruit {
  open fun getName() {
    println("fruit")
  }

  fun expirationDate() {

  }
}

class Apple : Fruit() {
  // ❌1: 'getName' hides member of supertype 'Fruit' and needs 'override' modifier
  // 如果子类重写了父类中的某个方法，在子类中的方法上 必须加上 override 关键字
  // fun getName() {
  //   println("apple")
  // }

  // ❌2: 'getName' in 'Fruit' is final and cannot be overridden
  // override fun getName() {
  //   println("apple")
  // }

  override fun getName() {
    println("apple")
  }
}

open class Orange : Fruit() {
  // 增加 final 关键字后，Orange 的子类不能重写 getName 方法
  final override fun getName() {
    println("orange")
  }
}

class OrangeChild : Orange() {
  // override fun getName() {
  //   println("orange child")
  // }
}

fun main() {
  val apple = Apple()
  apple.getName()
  apple.expirationDate()
}