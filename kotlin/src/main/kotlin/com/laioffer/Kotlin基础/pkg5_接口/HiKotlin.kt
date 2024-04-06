package Kotlin基础.pkg5_接口

interface A {
  // Kotlin的接口，可以只有声明，没有实现。
  fun method()
}

interface B {
  // 和Java8类似，方法前无需有default修饰符，Kotlin的接口中，可以有具体的实现
  fun method1() {
    println("B")
  }
}

// B后面不能有小括号，因为接口是不能实例化的
class C : B {

  override fun method1() {
    super.method1()
  }
}

fun main() {
  val c = C()
  c.method1()
}
