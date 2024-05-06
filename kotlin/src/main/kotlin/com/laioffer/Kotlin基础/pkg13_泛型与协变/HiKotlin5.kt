package Kotlin基础.pkg13_泛型与协变

/**
 * 类的描述: 协变类型的泛型
 * Created by 春夏秋冬在中南 on 2023/4/9 18:59
 */
class ParameterizedProducer<out T>(private val value: T) {
  fun get(): T {
    return this.value
  }
}

fun main() {
  val parameterizedProducer = ParameterizedProducer("Welcome")
  val myRef: ParameterizedProducer<Any> = parameterizedProducer // 将一个泛型为String类型的赋值给泛型为Any类型的

  // 上面👆两行代码相当于Java的
  // List<String> list = new ArrayList<>();
  // List<? extends Object> list1 = list; ? extends Object 相当于Kotlin中的out(协变)

  println(myRef is ParameterizedProducer<Any>) // true
}
