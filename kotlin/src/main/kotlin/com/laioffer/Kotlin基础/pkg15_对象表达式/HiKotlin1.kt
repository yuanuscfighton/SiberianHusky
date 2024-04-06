package Kotlin基础.pkg15_对象表达式

fun main() {

  // kotlin中，对象表达式 可以不实现任何接口，可以不继承任何抽象类
  // 匿名对象只能在局部变量范围内(在main函数里面)，才能被识别出其真正的类型。
  val myObject = object {

    init {
      println("初始化块执行了")
    }

    val myProperty = "hello world"

    fun myMethod() = "myMethod()"

  }

  println(myObject.myProperty)
  println(myObject.myMethod())
}
