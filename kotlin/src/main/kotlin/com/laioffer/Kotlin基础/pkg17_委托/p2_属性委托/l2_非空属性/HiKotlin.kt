package Kotlin基础.pkg17_委托.p2_属性委托.l2_非空属性

import kotlin.properties.Delegates

/**
 * 类的描述: 2.委托属性 -- 非空属性
 * Created by 春夏秋冬在中南 on 2023/4/15 20:12
 */
class MyPerson {
  // notNull适用于那些无法在初始化阶段就确定的属性值的场合
  var address: String by Delegates.notNull<String>()
}

fun main() {
  val myPerson = MyPerson()
  myPerson.address = "上海"
  println(myPerson.address)
}
