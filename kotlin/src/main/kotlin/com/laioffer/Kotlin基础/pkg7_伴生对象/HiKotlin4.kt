package Kotlin基础.pkg7_伴生对象

class HiKotlin4 {

  companion object MyObject {
    var a: Int = 100

    // 📢注意
    // （2）在JVM上，可以将伴生对象的成员真正生成为类的静态方法与静态属性，是通过@JvmStatic注解来实现的。
    // （3）伴生对象在编译后会生成一个静态内部类
    @JvmStatic
    fun method() {
      println("method invoked")
    }
  }
}

fun main() {
  HiKotlin4.method()
}

/*
(1) javap 反编译
public final class Kotlin学习.kotlin基础.pkg7_伴生对象.HiKotlin4 {
  public static final Kotlin学习.kotlin基础.pkg7_伴生对象.HiKotlin4$MyObject MyObject;
  public Kotlin学习.kotlin基础.pkg7_伴生对象.HiKotlin4();
  // 出现在HiKotlin4类中，真正成为了类的静态方法了。这就是@JvmStatic注解的作用
  public static final void method();
  public static final int access$getA$cp();
  public static final void access$setA$cp(int);
  static {};
}


(2) javap -c 将字节码反编译出来
public final class Kotlin学习.kotlin基础.pkg7_伴生对象.HiKotlin4 {
  public static final Kotlin学习.kotlin基础.pkg7_伴生对象.HiKotlin4$MyObject MyObject;

  public Kotlin学习.kotlin基础.pkg7_伴生对象.HiKotlin4();
    Code:
       0: aload_0
       1: invokespecial #8                  // Method java/lang/Object."<init>":()V
       4: return

  public static final void method();
    Code:
       0: getstatic     #16                 // Field MyObject:LKotlin学习/kotlin基础.pkg7_伴生对象/HiKotlin4$MyObject;
       3: invokevirtual #20                 // Method Kotlin学习/kotlin基础.pkg7_伴生对象/HiKotlin4$MyObject.method:()V
       6: return

  public static final int access$getA$cp();
    Code:
       0: getstatic     #26                 // Field a:I
       3: ireturn

  public static final void access$setA$cp(int);
    Code:
       0: iload_0
       1: putstatic     #26                 // Field a:I
       4: return

  static {};
    Code:
       0: new           #18                 // class Kotlin学习/kotlin基础.pkg7_伴生对象/HiKotlin4$MyObject
       3: dup
       4: aconst_null
       5: invokespecial #33                 // Method Kotlin学习/kotlin基础.pkg7_伴生对象/HiKotlin4$MyObject."<init>":(Lkotlin/jvm/internal/DefaultConstructorMarker;)V
       8: putstatic     #16                 // Field MyObject:LKotlin学习/kotlin基础.pkg7_伴生对象/HiKotlin4$MyObject;
      11: bipush        100
      13: putstatic     #26                 // Field a:I
      16: return
}


 */
