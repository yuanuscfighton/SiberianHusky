package Kotlin基础.pkg11_数据类

// 关于数据类成员的继承要点:
// 1.如果数据类中显式定义了equals、hashCode、toString方法，或者在数据类的父类中将这些方法声明为了final，那么编译器就不会再生成这些方法了，转而使用已有的。
//
// 2.如果父类拥有componentN方法，并且是open的以及返回兼容的类型，那么编译器就会在数据类中生成相应的componentN方法，
// 并且重写父类的这些方法；如果父类中的这些方法由于不兼容的签名或是被定义为final的，那么编译器就会报错。
//
// 3.在数据类中显式提供componentN方法以及copy方法实现是不允许的。

/////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////
// copy方法使用
data class Person1(val name: String, var age: Int, var address: String) {
  // ❌: Conflicting overloads: public final fun copy(name: String, age: Int, address: String): Unit defined in Kotlin基础.pkg10_数据类.Person1

  //  fun copy(name: String, age: Int, address: String) {
  //
  //  }
}

fun main() {
  val p1 = Person1("李四", 20, "上海")
  // copy的使用，再生成一个数据类，只是修改了其中的一个参数值
  val p2 = p1.copy(address = "北京")
  println(p2)

  // ⚠️: Parameter names should be specified explicitly for the 'copy()' method call
  // val p3 = p1.copy("广州") // 修改了name的属性值

  // 修改p1中的age属性值
  p1.age = 30
  println(p1)
}
