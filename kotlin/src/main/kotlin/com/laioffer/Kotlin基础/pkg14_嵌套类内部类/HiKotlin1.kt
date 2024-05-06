package Kotlin基础.pkg14_嵌套类内部类

/**
 * 类的描述: 内部类(Inner Class)
 * Created by 春夏秋冬在中南 on 2023/4/11 22:47
 */
class OuterClass1 {

  private val str: String = "hello world"

  // 「内部类」需要在class前面增加关键字 inner
  inner class InnerClass {
    // 在内部类中，可以访问到外部类中的成员变量
    // this@OuterClass1表示引用OutClass1的对象
    fun innerMethod() = this@OuterClass1.str // 在内部类中访问外部类中的属性
  }
}

fun main() {

  // 访问内部类中的方法，需要先创建外部类的实例，再创建内部类的实例，然后才能访问到内部类中的方法
  println(OuterClass1().InnerClass().innerMethod())
}

// 关于嵌套类与内部类之间的区别与联系:
// 1.嵌套类(Nested Class):
// （1）对应于Java的静态内部类(即有static关键字修饰的内部类)。
// （2）只要在一个类的内部定义了另外一个类，那么这个类就叫做嵌套类，相当于Java当中有static关键字修饰的内部类。
//
// 2.内部类(Inner Class):
// （1）对应于Java中的非静态内部类(即没有static关键字修饰的内部类)。
// （2）使用inner关键字在一个类的内部所定义的另外一个类就叫做内部类，相当于Java当中没有static关键字修饰的内部类。
