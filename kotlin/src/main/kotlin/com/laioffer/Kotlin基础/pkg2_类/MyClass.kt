package com.laioffer.Kotlin基础.pkg2_类

import java.util.Locale

// 'public' - Redundant visibility modifier
//public class MyClass1


// 在Kotlin中，一个类可以有一个primary构造方法以及一个或多个secondary构造方法
// primary构造方法是类头（class header）的一部分，它位于类名后面，可以拥有若干参数
// 主构造方法中是不能包含代码的

// Q: 什么时候constructor关键字可以省略？
// A: 如果primary构造方法没有任何注解或是可见性关键字修饰，那么constructor关键字可省略
// 如，class MyClass private constructor(username: String) {}，此时constructor关键字就不能省略

// 省略constructor关键字的写法
//class MyClass1 (username: String) {}

class MyClass constructor(username: String) {

  // 主构造方法中的参数，可以用在给属性赋初值
  private val mUsername: String = username.uppercase(Locale.ROOT)

  // 主构造方法中的参数，可以用在init代码中。
  // init是初始化代码块，给类的属性/成员变量赋初值的
  // 在初始化块中，可以直接使用构造方法中的参数的
  init {
    println(username)
  }
}

fun main() {
  val myClass = MyClass("zhangsan")
}