package com.laioffer.Kotlin基础.pkg4_继承与重写


open class Parent(name: String) {
}

/**
 * 在 Kotlin 中，如果一个类没有 primary 构造方法，那么这个类的每个 secondary 构造方法就需要通过 super 关键字来初始化父类型，
 * 或者通过其它 secondary 构造方法完成这个任务。不同的 secondary 构造方法可以调用父类型不同的构造方法
 */
class Child : Parent {

  constructor(name: String) : super(name) {

  }
}