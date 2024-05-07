package Kotlin基础.pkg17_委托.p2_属性委托.l3_可观测属性

import kotlin.properties.Delegates

/**
 * 类的描述: vetoable
 * Created by 春夏秋冬在中南 on 2023/4/15 20:34
 */

/**
 * 比较observable和vetoable:
 *
 * Delegate.observable接收两个参数: 初始值与修改处理器.
 * 处理器会在我们每次对属性赋值时调用，在赋值完成之后被调用
 * 处理器本身接收3个参数:被赋值的属性本身，旧的属性值与新的属性值
 *
 * Delegate.vetoable的调用时机与Delegate.observable相反，它是在对属性赋值之前被调用，
 * 根据Delegate.vetoable的返回结果是true还是false，来决定是否真正对属性进行赋值
 */
class Person1 {
  var age: Int by Delegates.vetoable(20) { _, oldValue, newValue ->
    when {
      oldValue <= newValue -> true
      else -> false
    }
  }
}

fun main() {
  val p1 = Person1()
  println(p1.age) // 20

  p1.age = 40
  println(p1.age) // 40

  p1.age = 30
  println(p1.age) // 40
}
