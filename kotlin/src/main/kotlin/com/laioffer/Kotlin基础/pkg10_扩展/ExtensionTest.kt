package Kotlin基础.pkg10_扩展

// 扩展: extension，如果想给某个类扩充功能，
// 方式1: 可以通过继承父类，在子类中增加方法/属性的方式实现 <-- 面向对象的特点
// 方式2: 使用装饰模式，i.e. 在不修改类的条件下，动态的给某个类增加功能
class ExtensionTest {

  fun add(a: Int, b: Int) = a + b

  fun sub(a: Int, b: Int) = a - b
}

// 给ExtensionTest增加一个乘法操作
// multiply叫作ExtensionTest类的扩展函数(扩展方法)，
// 相当于ExtensionTest类拥有了multiply这个方法，实际上multiply方法并没有被插入到ExtensionTest类里面。
fun ExtensionTest.multiply(a: Int, b: Int) = a * b


fun main() {
  val extensionTest = ExtensionTest()
  println(extensionTest.add(1, 2))
  println(extensionTest.multiply(2, 3))
}

// 反编译
/*
public final class Kotlin基础.pkg10_扩展.ExtensionTest {
  public Kotlin基础.pkg10_扩展.ExtensionTest(); // 构造器
    Code:
       0: aload_0
       1: invokespecial #8                  // Method java/lang/Object."<init>":()V
       4: return

  public final int add(int, int); // add方法
    Code:
       0: iload_1
       1: iload_2
       2: iadd
       3: ireturn

  public final int sub(int, int); // sub方法
    Code:
       0: iload_1
       1: iload_2
       2: isub
       3: ireturn

  // 1.扩展函数的解析是静态的，扩展本身并不会真正修改原有的类，也就是说它并不会在原有的类中插入新的属性或者方法 --> 可以通过查看字节码进行验证
  // 反编译ExtensionTest.kt类
  // 命令: javap -c "Kotlin/build/classes/kotlin/main/Kotlin基础/pkg10_扩展/ExtensionTest.class"

  // 结论:
  // 没有multiply方法 ==> 验证了，扩展本身不会将扩展的方法插入到已有的类中
}
 */

// 扩展函数的解析是静态的
// 1.扩展本身并不会真正修改原有的类，也就是说它并不会在原有的类中插入新的属性或者方法 --> 可以通过查看字节码进行验证
// 2.扩展函数的解析是静态分发的，而不是动态的，i.e. 不支持多态，调用只取决于对象的声明类型
// 3.调用是由对象声明类型所决定的，而不是由对象的实际类型决定