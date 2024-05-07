package Kotlin基础.pkg14_嵌套类内部类

class Person(val name: String, var age: Int) {

  private val str = "Person属性"

  // 内部类
  // 定义好了一个内部类，当程序在运行的时候，如果创建好一个外部类对象后，内部类会持有对外部类的一个引用，否则访问不到Person#method()方法
  private inner class PersonFeature(var height: Int, var weight: Int) {

    private val str: String = "PersonFeature属性"

    fun getPersonFeature() {
      val str = "局部变量"
      println("身高=$height, 体重=$weight")
      this@Person.method()

      println(this@Person.str)  // Person属性
      println(this.str)  // PersonFeature属性
      println(str)  // 局部变量
      // Kotlin访问外部类变量的方式: this@OuterClass.str
      // Java访问外部类变量的方式: OuterClass.this.str
    }
  }

  private fun method() {
    println("执行了Person中的method()方法")
  }

  fun getPerson() {
    val personFeature = PersonFeature(180, 120)
    personFeature.getPersonFeature()
  }
}

fun main() {
  val person = Person("张三", 20)
  person.getPerson()
}
/*


静态不属于类的本身，而是属于对应的Class对象。如，静态的方法不属于定义方法的那个类，而是属于定义这个方法的类的Class对象。
 */

// 在运行的时候，当创建好外部类的实例的时候，它的PersonFeature这个内部类会持有一个对于外部类对象的引用。否则访问不到外部类中的方法。
// 在内部类中，会持有对于外部类对象的引用，这样子，内部类可以访问外部类中的成员变量或方法
// 如果外部类想访问内部类中的属性/方法，就需要先创建外部类的实例
