package Kotlin基础.pkg14_嵌套类内部类

// 在内部类中，可以访问到外部类中定义的成员变量
// 嵌套类不能访问外部类的其它成员，只能访问另一个嵌套类
class OuterClass4 {

  private val str: String = "hello world"

  // 嵌套内部类，相当于Java中的静态内部类，(Kotlin中没有static关键字的)
  class NestedClass41 {
    fun nestedMethod() = "welcome"
    // 此处不可能访问到外部定义的str变量，因为静态的只能访问静态的，不能访问非静态的。
  }

  class NestedClass42 {
    // 在一个嵌套类中，使用了另一个嵌套类。因为静态的可以访问静态的，所以可以访问NestedClass41
    val nestedClass = NestedClass41()
  }
}

// 在Java中，
// 1.静态的只能访问静态的，不能访问非静态的。即，在一个类的静态方法中，不可能访问这个类中的非静态成员变量
// 2.非静态的可以访问静态的或者非静态的。
//
// 原因: 静态的不属于当前类的本身，而是属于这个类对应的Class对象。
// JVM在进行加载、链接、初始的时候，首先初始化的是这个类的静态成员，而非静态的成员只有在类被实例化的时候，才能被使用。
