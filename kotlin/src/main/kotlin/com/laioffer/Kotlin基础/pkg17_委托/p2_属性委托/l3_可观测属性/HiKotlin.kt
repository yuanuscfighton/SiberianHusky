package Kotlin基础.pkg17_委托.p2_属性委托.l3_可观测属性

import kotlin.properties.Delegates

/**
 * 类的描述: 2.委托属性 -- 可观测属性
 * Created by 春夏秋冬在中南 on 2023/4/15 20:19
 */
class Person {

  // param1: initialValue
  // param2: onChange: (property: KProperty<*>, oldValue: T, newValue: T) -> Unit
  //              onChange方法:
  //                  (1) 接收3个参数
  //                      (i) property (ii) oldValue (iii) newValue
  //                  (2) 返回值是Unit
  var age: Int by Delegates.observable(20) { prop, oldValue, newValue ->
    println("${prop.name}, oldValue: $oldValue, newValue: $newValue")
  }
}

fun main() {
  val p = Person()
  p.age = 30
  p.age = 40
}
