package Kotlin基础.pkg14_嵌套类内部类

/**
 * 类的描述: 在外部类的外面，创建内部类的对象
 * Created by 春夏秋冬在中南 on 2023/4/12 23:07
 */
class OuterClass6 {

  inner class NestedClass6(str: String) {
    init {
      println("str:$str")
    }
  }
}

fun main() {
  val nestedClass2: OuterClass6.NestedClass6 = OuterClass6().NestedClass6("hello world")
}
