package Kotlin基础.pkg17_委托.p2_属性委托.l4_map委托

class Student1(map: MutableMap<String, Any?>) {
  var address: String by map
}

fun main() {
  //  val map = mutableMapOf(
  //    "address" to "北京"
  //  )

  // ❌ 错误信息
  // Type mismatch.
  // Required: MutableMap<String, Any>
  // Found: MutableMap<String, String>
  // val s1 = Student1(map) // 此处kotlin会自动推断出map的K,V类型都是String类型

  val map: MutableMap<String, Any?> = mutableMapOf(
    "address" to "北京!"
  )

  val s1 = Student1(map)
  println(s1.address)

  println("\n--------------------\n")

  s1.address = "上海!"
  println(map["address"])
  println(s1.address)
}
