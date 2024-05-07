package Kotlin学习.pkg17_lambda表达式

fun String.filter(predicate: (Char) -> Boolean): String {
  val sb = StringBuilder()
  for (index in 0 until length) {
    val ele = get(index)
    if (predicate(ele)) {
      sb.append(ele)
    }
  }
  return sb.toString()
}

fun main() {
  val s = "abc2def9xy7"
  println(s.filter {
    x -> x.isLetter()
  })
}
