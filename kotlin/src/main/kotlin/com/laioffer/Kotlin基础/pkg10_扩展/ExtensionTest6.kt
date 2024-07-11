package Kotlin基础.pkg10_扩展

// 扩展的作用域
// 1.扩展函数所定义在的类实例叫做分发接收者（dispatch receiver）
// 2.扩展函数所扩展的那个类的实例叫做扩展接收者（extension receiver）
// 3.当以上两个名字出现冲突时，扩展接收者的优先级最高

// 代码编写顺序
// (1) 定义DD类，在DD类中定义method方法
// (2) 定义EE类，在EE类中定义method2方法
// (3) 在EE类中，定义一个对DD类的扩展方法 hello，在hello方法中，可以使用DD类中已经定义的method方法

class DD { // DD叫作扩展接收者

  fun method() {
    println("DD method")
  }

  override fun toString(): String {
    return "DD中的toString"
  }
}

class EE { // EE叫作分发接收者
  private fun method2() {
    println("method2")
  }

  // 在另一个类中定义的DD类的扩展，不是在顶层声明的，因此这里声明的DD的扩展不能在EE类之外使用
  fun DD.hello() {
    // DD.hello()这个方法是对DD进行扩展，则就可以使用DD中的成员或者方法，因此这里可以调用DD#method()
    method()

    // 还可以在这里使用 定义DD.hello()扩展方法所在类中的成员或者方法，因此这里可以调用EE#method2()
    method2()
  }

  fun world(dd: DD) {
    dd.hello()
  }

  override fun toString(): String {
    return "EE中的toString"
  }

  // 这里声明的DD的扩展不能在EE类之外使用
  private fun DD.output() {
    // 当以上两个名字出现冲突时，扩展接收者的优先级最高
    println(toString()) // 这里调用的是DD中的toString方法
    println(this.toString())
    /////////////////////////////////////////////////
    println(this@EE.toString()) // 调用的是EE中的toString方法
  }

  fun test() {
    val dd = DD()
    dd.output()
  }
}

fun main() {
  val dd = DD()
  dd.method()
  // dd.output() // 因为扩展方法output不是定义在顶层的

  println("----------")
  val ee = EE()
  ee.test()
}

// 扩展优点:
// 扩展可以很好地解决Java中充斥的各种辅助类问题。
// Collections.swap(list, 4, 10)
// list.swap(4, 10)
// Collections.binarySearch()
// list.binarySearch(...)
