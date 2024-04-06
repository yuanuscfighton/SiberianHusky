package Kotlin基础.pkg13_泛型与协变

/**
 * 类的描述:
 * Created by 春夏秋冬在中南 on 2023/4/9 18:57
 */
class ParameterizedClass<A>(private val value: A) {
  fun getValue(): A {
    return this.value
  }
}

fun main() {
  val parameterizedClass = ParameterizedClass<String>("Hello World")
  val result = parameterizedClass.getValue()

  println(result is String)
}
