package Kotlin基础.pkg15_对象表达式

interface MyInterface2 {
  fun myPrint(i: Int)
}

abstract class MyAbstractClass {
  abstract val age: Int

  abstract fun printMyAbstractClassInfo()
}

fun main() {

  // Kotlin可以有若干个父类型
  val myObject = object : MyInterface2, MyAbstractClass() {

    override fun myPrint(i: Int) {
      println("i的值是$i")
    }

    override val age: Int
      get() = 10

    override fun printMyAbstractClassInfo() {
      println("printMyAbstractClassInfo() invoked")
    }
  }

  myObject.myPrint(200)
  println(myObject.age)
  myObject.printMyAbstractClassInfo()
}
