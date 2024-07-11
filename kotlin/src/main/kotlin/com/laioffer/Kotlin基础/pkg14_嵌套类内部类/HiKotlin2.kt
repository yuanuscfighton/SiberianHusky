package Kotlin基础.pkg14_嵌套类内部类

/**
 * 类的描述: 局部嵌套类
 * Created by 春夏秋冬在中南 on 2023/4/11 22:54
 */
class OuterClass2 {

  // 局部嵌套类: 定义在一个方法中的嵌套类
  fun getName(): String {

    // 定义一个局部嵌套类
    class LocalNestedClass {
      val name: String = "local-nested-class"
    }

    val localNestedClazz = LocalNestedClass()
    return localNestedClazz.name
  }
}

fun main() {
  println(OuterClass2().getName())
}
