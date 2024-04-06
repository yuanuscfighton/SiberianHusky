package Kotlin基础.pkg7_伴生对象

/**
 * @description object declaration -- 对象声明
 * 什么是「对象声明」:
 * 1.面向对象: Class类的概念，Java中一定要通过类来创建(new)一个对象(实例)的，这样子对象(实例)才能在内存中创建出来。
 * 2.在Kotlin中，可以直接定义(声明)一个对象
 *
 * 声明对象使用object关键字
 *
 * @date 2023/1/30 22:25
 */
// 下面👇🏻代码的含义: 声明了一个对象，这个对象的名字叫作MyObject。对象可以拥有属性和方法的，和类是一样的
object MyObject {
  fun method() {
    println("MyObject对象中的方法")
  }
}

fun main() {
  // MyObject本身就是一个对象，不需要像类一样创建一个实例。这里可以直接调用对象中声明的方法了。
  MyObject.method()
}
