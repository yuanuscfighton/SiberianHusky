package Kotlin基础.pkg8_属性

class Person1(address: String, name: String) {

  ////////// 1.常量 (只读，不可写) ////////////////////
  // 常量的定义方式，(只读属性: [1]用val关键字声明的，[2]只有get方法，没有set方法)
  val age: Int
    get() = 20 // 初始值是20

  // Int显式声明类型可以省略
  val age1
    get() = 6

  ////////// 2.变量 (可读可写) ////////////////////
  // 变量的定义方式，这里还是使用的默认的setter和getter方法
  // var address: String = address

  var address: String = address // 等号右边的address是构造方法中的值
    get() {
      println("getter invoked. field=$field")
      return field // field称作backing field
      // 当我们调用address的属性的时候，其实是调用了getter/setter方法。getter/setter方法访问的事务本身是field。
      // field只能用在属性的getter/setter内，filed用于指代address背后真正存储值的事务。
    }
    set(value) {
      field = value
      println("setter invoked. filed=$field")
    }
}

/*
    属性声明的完整形式:
      var propertyName: propertyType = initializer
          getter()...
          setter()...

    backing field，支撑字段（域），用于指代address背后真正存储值的事务
    backing property，支撑属性
 */

fun main() {
  val p1 = Person1("上海", "张三")
  println(p1.age)

  println(p1.address)

  p1.address = "北京"
  println(p1.address)
}
