package Kotlin基础.pkg16_枚举

enum class Season2(val temperature: Int) {
  SPRING(10), SUMMER(30), AUTUMN(20), WINTER(-10)
}

fun main() {
  println(Season2.SPRING.temperature)
}

