package com.laioffer.Kotlin基础.pkg7_伴生对象

/**
 * 类描述: companion object -- 伴生对象
 *
 * Java 中，静态方法不属于它所在的类，而是属于它所在的类对应的 Class 对象。
 * 对于一个类来说，无论它生成多少个实例对象，这些实例对象都会映射到同一个 Class 对象。
 * Class 对象描述了一个类里面的一种数据结构，它也是反射的源头。
 *
 * 那么对于类中的静态方法来说，可以当作是全局方法，可以通过类的名字直接调用。
 * ***********************************************************************************
 * Kotlin 中，和Java不同的是，类是没有 static 方法的。
 * 在大多数情况下，Kotlin 推荐的做法是使用包级别的函数来作为静态方法。
 * Kotlin 会将包级别的函数当做静态方法来看待
 *
 * 伴生对象，就是说这个对象的声明是位于一个类中的，即，随着类的存在而存在，这样的对象，我们叫作伴生对象。
 *
 * HiKotlin1 这个类中，是没有定义任何成员变量和方法的，都是定义在 MyObject 这个伴生对象中。
 *
 *
 * created by 春夏秋冬在中南 on 2023/1/30 22:26
 */
class HiKotlin1 {

  /**
   * 如果不提供伴生对象的名字，那么编译器会提供一个默认的名字 Companion,
   */
  companion object MyObject {

    // 在伴生对象中可以定义成员变量、成员方法
    var a: Int = 100

    fun method() {
      println("method invoked")
    }
  }

  // ❌错误信息: Only one companion object is allowed per class
  // 每一个类中，最多只允许有一个 companion object
  // companion object MyObject2 {
  //
  // }
}

fun main() {

  // 调用 method() 方法
  HiKotlin1.MyObject.method()
  println(HiKotlin1.MyObject.a)

  println("----- 分割线 -----")

  /////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////

  // 这里直接是类名.方法，[类似于静态方法(但Kotlin中没有静态方法)]
  HiKotlin1.method()
  // 访问类中的静态属性
  println(HiKotlin1.a)
}
