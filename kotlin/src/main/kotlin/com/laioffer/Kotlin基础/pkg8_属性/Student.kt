package Kotlin基础.pkg8_属性

/**
 * 延迟初始化属性
 *
 * Kotlin要求非空类型的属性必须要在构造方法中进行初始化
 * 有时，这种要求不太方便，比如可以通过依赖注入或是单元测试情况下进行属性的赋值。
 *
 * 通过lateinit关键字标识属性为延迟初始化，需要满足如下3个条件:
 * 1.lateinit只能用在类体中声明的var属性上，不能用在primary constructor声明的属性上
 * 2.属性不能拥有自定义的setter与getter
 * 3.属性类型需要为非空，且不能是基本数据类型
 */
class Student {
  lateinit var name: String

  // ❌
  // 'lateinit' modifier is not allowed on properties with a custom getter or setter
  // 属性不能拥有自定义的setter与getter
  //  lateinit var address: String
  //      set(value) {
  //          field = value
  //      }

  // 'lateinit' modifier is not allowed on properties of primitive types
  // 属性类型需要为非空，且不能是基本数据类型
  // lateinit var age: Int

  fun init() {
    this.name = "张三"
  }

  fun printInfo() {
    println(this.name)
  }
}

fun main() {
  val student = Student()
  student.init()
  student.printInfo()
}
