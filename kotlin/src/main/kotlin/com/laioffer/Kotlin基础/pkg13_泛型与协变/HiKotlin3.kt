package Kotlin基础.pkg13_泛型与协变

// out 表示出去，读取的，T类型只是用来读取的，不会用来修改它的值
class MyClass3<out T, in M>(t: T, m: M) {
  private val t: T
  private var m: M

  init {
    this.t = t
    this.m = m
  }

  fun getT(): T = this.t // 对于t，我们只是读取，没有写入 ==> 这里是协变

  fun setM(m: M) {
    this.m = m
  }
}

fun test3(myClz3: MyClass3<String, Number>) {
  // 如果不在MyClass3<T>中增加out，此处就会报错，因为不允许直接把MyClass3<String>赋给MyClass3<Any>
  // 所以需要增加 out关键字
  val myObj: MyClass3<Any, Int> = myClz3 // 把一个String类型的映射给Any，读取的时候都是以Any类型读取的

  println(myObj.getT())
}

fun main() {
  val myClz = MyClass3<String, Number>("myClass3", 2)
  test3(myClz)
}
