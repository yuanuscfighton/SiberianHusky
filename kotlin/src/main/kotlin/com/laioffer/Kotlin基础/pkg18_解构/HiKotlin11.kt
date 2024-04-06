package Kotlin基础.pkg18_解构

fun main() {
  // 快照
  // toList扩展方法只是复制原集合中的元素，所以返回的集合就可以确保不会发生变化
  val items = mutableListOf("a", "b", "c")
  val items2 = items.toList()

  items.add("d")

  println(items)
  println(items2)
}
