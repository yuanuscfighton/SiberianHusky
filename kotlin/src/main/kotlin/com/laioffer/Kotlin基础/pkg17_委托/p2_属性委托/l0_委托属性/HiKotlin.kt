package Kotlin基础.pkg17_委托.p2_属性委托.l0_委托属性

import kotlin.reflect.KProperty

/**
 * 类的描述: 委托属性:
 * Created by 春夏秋冬在中南 on 2023/4/15 20:17
 */

// 有4种情况在实际开发中比较有用: 延迟属性、可观测属性、非空属性、map属性

// 2.委托属性:
// (1) 定义一个普通的属性
// (2) 自己定义一个委托类

// 定义一个委托类
// 对于被委托的属性，setter和getter方法就不是由kotlin编译器为其生成了，而是转移给委托方
class MyDelegate {
  // param1 thisRef: 被委托属性对象
  // param2 property: 被委托属性的本身
  operator fun getValue(thisRef: Any?, property: KProperty<*>): String =
    "$thisRef, your delegated property name is ${property.name}"
  // 输出: Kotlin基础.pkg15_委托.p1_类委托.MyPropertyClass@5197848c, new value is hello world

  operator fun setValue(thisRef: Any?, property: KProperty<*>, newVal: String) =
    println("$thisRef, new value is $newVal")
}

class MyPropertyClass {
  var str: String by MyDelegate()
  // 将str的setter和getter方法转移给MyDelegate(委托方)来做
}

fun main() {
  val myPropertyClass = MyPropertyClass()

  // 输出: Kotlin基础.pkg15_委托.p1_类委托.MyPropertyClass@5197848c, new value is hello world
  myPropertyClass.str = "hello world"
  // 输出: Kotlin基础.pkg15_委托.p1_类委托.MyPropertyClass@5197848c, your delegated property name is str
  println(myPropertyClass.str)
}

/**
 * 有4种情况在实际开发中比较有用:
 * 1.延迟属性: 第1次访问属性的时候，会执行一次计算，后面再次访问的时候，就不再执行计算了，而是使用第一次缓存的结果
 * 2.可观测属性: 当给一个属性赋值的时候，相当于这个属性有监听器，在赋值之前/之后，监听器都会收到通知
 * 3.非空属性: 属性不能是空的
 * 4.map属性: 对于类中的所有属性，将它们委托给一个map对象，由这个map对象统一管理
 */
