package Kotlin基础.pkg7_伴生对象

class HiKotlin3 {

  companion object MyObject {

    var a: Int = 100

    fun method() {
      println("method invoked")
    }
  }
}

fun main() {

  val v = HiKotlin3.MyObject
  println(v.javaClass) // 输出 class Kotlin基础.pkg7_伴生对象.HiKotlin3$MyObject
  // MyObject类型是Kotlin基础.pkg7_伴生对象.HiKotlin3$MyObject，class名字叫作MyObject，而且还是HiKotlin3的一个内部类。

  // .javaClass - Returns the runtime Java class of this object.（返回这个对象在运行期所对应的Java类型）

  // 📢注意
  // （1）虽然伴生对象的成员看起来像是Java中的静态成员，但在运行期，他们依旧是真实对象的实例成员
  // 具体看: method方法从使用角度看，就好像是HiKotlin3类中的一个静态方法。但其实并不是静态方法。

}

/*
(1) javap 反编译：进入到 "Kotlin学习/Kotlin学习/"目录，"javap kotlin基础.pkg7_伴生对象.HiKotlin3"
public final class Kotlin学习.kotlin基础.pkg7_伴生对象.HiKotlin3 {
  // 验证: (1)虽然伴生对象的成员看起来像是Java中的静态成员，但在运行期，他们依旧是真实对象的实例成员
  // method()方法并没有出现，HiKotlin3.method()，中间还是通过MyObject做了一层转换，最终转换成，HiKotlin3访问它里面的MyObject，通过对象调用method()方法。
  // 这就是定义的companion object，类型是Kotlin学习.kotlin基础.pkg7_伴生对象.HiKotlin3$MyObject(静态内部类的类型)，且是静态的static
  public static final Kotlin学习.kotlin基础.pkg7_伴生对象.HiKotlin3$MyObject MyObject;
  public Kotlin学习.kotlin基础.pkg7_伴生对象.HiKotlin3();
  public static final int access$getA$cp();
  public static final void access$setA$cp(int);
  static {};
}


(2) javap -c 将字节码反编译出来
// 说明: 这里没有出现method()静态方法，这就验证了这句话「虽然伴生对象的成员看起来像是Java中的静态成员，
但在运行期，他们依旧是真实对象(真实对象指的是MyObject)的实例成员」，==> method()方法并不会成为HiKotlin3类的静态成员

public final class Kotlin学习.kotlin基础.pkg7_伴生对象.HiKotlin3 {
  public static final Kotlin学习.kotlin基础.pkg7_伴生对象.HiKotlin3$MyObject MyObject;

  // HiKotlin3的构造方法
  public Kotlin学习.kotlin基础.pkg7_伴生对象.HiKotlin3();
    Code:
       0: aload_0
       1: invokespecial #8                  // Method java/lang/Object."<init>":()V
       4: return

  public static final int access$getA$cp();
    Code:
       0: getstatic     #16                 // Field a:I
       3: ireturn

  public static final void access$setA$cp(int);
    Code:
       0: iload_0
       1: putstatic     #16                 // Field a:I
       4: return

  // 静态代码块
  static {};
    Code:
       0: new           #22                 // class Kotlin学习/kotlin基础.pkg7_伴生对象/HiKotlin3$MyObject
       3: dup
       4: aconst_null
       5: invokespecial #25                 // Method Kotlin学习/kotlin基础.pkg7_伴生对象/HiKotlin3$MyObject."<init>":(Lkotlin/jvm/internal/DefaultConstructorMarker;)V
       8: putstatic     #29                 // Field MyObject:LKotlin学习/kotlin基础.pkg7_伴生对象/HiKotlin3$MyObject;
      11: bipush        100
      13: putstatic     #16                 // Field a:I
      16: return
}


 */
