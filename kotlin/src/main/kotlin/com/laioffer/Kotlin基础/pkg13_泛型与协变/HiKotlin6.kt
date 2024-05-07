package Kotlin基础.pkg13_泛型与协变

class ParameterizedConsumer<in T> {
  fun toString(value: T): String {
    return value.toString()
  }
}

fun main() {
  val parameterizedConsumer = ParameterizedConsumer<Number>()
  val myRef: ParameterizedConsumer<Int> = parameterizedConsumer

  // 相当于Java中的
  // List<Object> list = new ArrayList<>();
  // List<? super String> list1 = list;

  println(myRef is ParameterizedConsumer<Int>) // true
}
