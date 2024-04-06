package Kotlin基础.pkg18_解构

fun main() {
  val map = mapOf<String, String>("a" to "aa", "b" to "bb", "c" to "cc")
  for ((key, value) in map) {
    println("key: $key, value: $value")
  }

  println("----------")
  println("----------")

  map.mapValues { entry -> "${entry.value}+hello" }.forEach { println(it) }

  println("----------")
  println("----------")

  // 解构声明
//  map.mapValues { (key, value) -> "$value-hi" }.forEach { println(it) }
  map.mapValues { (_, value) -> "$value-hi" }.forEach { println(it) }

  println("----------")
  println("----------")

  // Kotlin允许我们为解构声明整体指定类型，
  map.mapValues { (_, value): Map.Entry<String, String> -> "$value+Java" }.forEach { println(it) }
  // 也可以为每一个具体的component指定类型
  map.mapValues { (_, value: String) -> "$value+Kotlin" }.forEach { println(it) }
}
