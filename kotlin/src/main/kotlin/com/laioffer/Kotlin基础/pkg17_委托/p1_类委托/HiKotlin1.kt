package Kotlin基础.pkg17_委托.p1_类委托

interface MyInterface1 {
  fun myCode()
}

class MyInterfaceImpl1(val str: String): MyInterface1 {
  override fun myCode() {
    println("Hi $str")
  }
}

class MyClass1(myInterface: MyInterface1): MyInterface1 by myInterface {
  // 如果自己也重写了myCode()方法，则优先使用自己的
  override fun myCode() {
    println("Hello World")
  }
}

fun main() {
  val myInterfaceImpl = MyInterfaceImpl1("李四")
  MyClass1(myInterfaceImpl).myCode() // Hello World
}



