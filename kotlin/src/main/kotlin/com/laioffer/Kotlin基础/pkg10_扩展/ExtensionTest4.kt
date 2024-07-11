package Kotlin基础.pkg10_扩展

/**
 * 扩展属性
 */
class ExtensionProperty

// 增加一个name属性
val ExtensionProperty.name: String
  get() = "张三"

fun main() {
  val extensionProperty = ExtensionProperty()
  println(extensionProperty.name)
}
