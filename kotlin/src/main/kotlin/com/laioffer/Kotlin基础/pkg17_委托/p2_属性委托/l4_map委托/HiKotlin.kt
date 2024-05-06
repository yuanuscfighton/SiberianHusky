package Kotlin基础.pkg17_委托.p2_属性委托.l4_map委托

import java.util.Date

/**
 * 类的描述: map委托
 * Created by 春夏秋冬在中南 on 2023/4/15 20:39
 */

/**
 * map委托: 将属性存储到map中
 *
 * 常见的应用场景:将属性值存储到map中，在这种情况中，可以将map实例作为委托，作为类中属性的委托。
 */
class Student(map: Map<String, Any?>) {

  val name: String by map

  val address: String by map

  val age: Int by map

  val birthday: Date by map
}

fun main() {
  // 注意: map中key的名字要与类中的属性名字保持一致
  val s = Student(
    mapOf(
      "name" to "张三",
      "address" to "上海",
      "age" to 20,
      "birthday" to Date()
    )
  )

  println(s.name)
  println(s.address)
  println(s.age)
  println(s.birthday)

}
