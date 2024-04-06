package Kotlin基础.pkg3_构造方法

/**
 * Student3这个类只有一个primary构造方法，没有secondary构造方法
 *
 * 在JVM上，如果类的primary构造方法的所有参数都拥有默认值，
 * 那么kotlin编译器就会为这个类生成一个不带参数的构造方法，
 * 这个不带参数的构造方法会使用这些参数的默认值，这样做的目的在于可以跟Spring等框架更好地集成。
 */
class Student3(username: String = "张三") {}

class Student33(val username: String = "李四")

class Student333(private val username: String = "王五") {}

fun main() {
  val student = Student33()
  println(student.username)

  val student1 = Student33("张三")
  println(student1.username)

  ///////////////////////////////////////
  val student33 = Student33()
  // 因为Student33类中的username属性是private的，所以这里访问不到
//  println(student33.username)

}
