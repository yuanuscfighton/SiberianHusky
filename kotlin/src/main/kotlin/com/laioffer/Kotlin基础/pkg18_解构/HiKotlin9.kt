package Kotlin基础.pkg18_解构

// Kotlin严格区分可变集合与不可变集合
// 要清楚的一点是：区分开可变集合的只读视图与实际上真正的不可变集合

fun main() {
  val stringList: MutableList<String> = mutableListOf("hello", "world", "hello world")
  val readOnlyView: List<String> = stringList

  println(stringList)

  stringList.add("welcome")

  println(readOnlyView)

//  readOnlyView.clear()
//  stringList.clear()
}
