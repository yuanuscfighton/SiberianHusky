package Kotlin基础.pkg15_对象表达式

// 匿名对象只能在局部变量范围内或是被private修饰的成员变量范围内才能被识别出其真正的类型。
////////////////////////////////////////////////////////////////////////////
// 如果将匿名对象当做一个public方法的返回类型或是public属性的类型，那么该方法或是属性的真正类型就是该匿名对象所声明的父类型。
// 如果没有声明任何父类型，那么其类型就是Any；在这种情况下，匿名对象中所声明的任何成员都是无法访问的。

class MyClass {

  // 此处的匿名对象的父类型是没有声明的，那么其类型就是Any类型，如果是Any类型，显然Any中是没有output方法的
  // 如果有父类型，应该在myObject后面声明，e.g. myObject:Xxx
  // 因此，匿名对象只能在局部变量范围内或是被private修饰的成员变量范围内才能被识别出其真正的类型
  private var myObject = object {
    fun output() {
      println("output invoked")
    }
  }

  fun myTest() {
    println(myObject.javaClass) // 打印对象的类型，class Kotlin基础.pkg13_对象表达式.MyClass$myObject$1，$1意思是在一个类中可以定义多个匿名对象
    myObject.output()
  }
}

fun main() {
  val myClass = MyClass()
  myClass.myTest()
}
