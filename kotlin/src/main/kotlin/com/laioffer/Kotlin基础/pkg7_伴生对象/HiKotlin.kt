package com.laioffer.Kotlin基础.pkg7_伴生对象

/**
 * 类的描述: object declaration -- 对象声明
 *
 * 什么是「对象声明」:
 * 1.面向对象: Class 类的概念，Java 中一定要通过类来创建(new)一个对象(实例)的，这样子对象(实例)才能在内存中创建出来。
 * 2.在 Kotlin 中，可以直接定义(声明)一个对象
 *
 * 声明对象使用 object 关键字
 *
 * Created by 春夏秋冬在中南 on 2023/1/30 22:25
 */
// 下面👇🏻代码的含义: 声明了一个对象，这个对象的名字叫作 MyObject
// 对象可以拥有属性和方法的，和类是一样的
object MyObject {
  fun method() {
    println("MyObject对象中的方法")
  }
}

fun main() {
  // MyObject 本身就是一个对象，不需要像类一样创建一个实例，这里可以直接调用对象中声明的方法了
  MyObject.method()
}
