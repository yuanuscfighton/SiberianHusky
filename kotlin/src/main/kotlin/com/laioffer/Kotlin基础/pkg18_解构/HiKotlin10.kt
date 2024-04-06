package Kotlin基础.pkg18_解构

fun main() {
  // 只读类型是协变的，因为它只用于从集合中获取数据，而不会修改集合中的数据
  val s = listOf("a", "b")
  val s2: List<Any> = s

}
