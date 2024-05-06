package Kotlin基础.pkg13_泛型与协变

/**
 * 泛型(generic)，表示变量类型的参数化
 */

// T表示泛型的类型，primary constructor(主构造方法)接收一个参数，这个参数是T类型的。
class MyGeneric<T>(t: T) {
  var myVariable: T

  init {
    this.myVariable = t
  }
}

fun main() {
  // 泛型的完整写法
  val myGeneric: MyGeneric<String> = MyGeneric<String>("HelloWorld")
  println(myGeneric.myVariable)

  // 借助于类型推断
  val myGeneric1 = MyGeneric("Hi Kotlin")
  println(myGeneric1.myVariable)
}
