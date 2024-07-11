package Kotlin基础.pkg17_委托.p1_类委托

// 委托(delegation)
// 什么是委托: 有A和B两个人，原本想让A去做一件事，A自己不做，把事情转交给B，B去完成原本由A去做的事情。此时，我们称B是A的委托。

// 1.类的委托:
// 委托的实现步骤:
// step1 定义接口MyInterface
// step2 实现接口
// step3 定义MyClass，MyClass委托MyInterfaceImpl去myPrint()

/**
 * 第1步: 定义接口
 */
interface MyInterface {
  fun myPrint()
}

/**
 * 第2步: 实现接口
 */
class MyInterfaceImpl(private val str: String) : MyInterface {
  override fun myPrint() {
    println("welcome $str")
  }
}

// MyClass这个类不打算自己实现myPrint()方法，而是把myPrint()的实现转交给MyInterfaceImpl类，
// 即，把MyClass的myPrint()方法的内容 委托给MyInterfaceImpl这个类的一个实例 代替MyClass去完成myPrint()方法
//////////
// 在Kotlin中，通过[by]关键字 + [委托的目标对象]，即 MyClass构造方法中的参数
class MyClass(myInterface: MyInterface) : MyInterface by myInterface

fun main() {
  val myInterfaceImpl = MyInterfaceImpl("张三")
  MyClass(myInterfaceImpl).myPrint()
}

// 委托原理:
// by关键字后面的对象实际上会被存储在类的内部(反编译后，putField)，编译器则会父接口中的所有方法实现出来，并且将实现转移给委托对象来去进行
// putField + getField

/*
## 解读反编译后的代码:

// [1] MyClass实现了MyInterface接口
public final class Kotlin基础.pkg15_委托.p1_类委托.MyClass implements Kotlin学习.kotlin基础.pkg15_委托.MyInterface {

  // [2] MyClass有一个带参数的构造方法，接收一个MyInterface对象
  public Kotlin基础.pkg15_委托.p1_类委托.MyClass(Kotlin学习.kotlin基础.pkg15_委托.MyInterface);
    Code:
       0: aload_1
       1: ldc           #11          // String myInterface
       3: invokestatic  #17          // Method kotlin/jvm/internal/Intrinsics.checkNotNullParameter:(Ljava/lang/Object;Ljava/lang/String;)V
       6: aload_0
       7: invokespecial #20          // Method java/lang/Object."<init>":()V
      10: aload_0
      11: aload_1
      // [3] putfield: 将传进来的MyInterface参数保存起来
      12: putfield      #24          // Field $$delegate_0:LKotlin基础.pkg15_委托/MyInterface;
      15: return

  // 重写了myPrint()方法
  public void myPrint();
    Code:
       0: aload_0
       // [4] 取出刚保存的MyInterface参数
       1: getfield      #24          // Field $$delegate_0:LKotlin基础.pkg15_委托/MyInterface;
       // [5] 调用myPrint()参数
       4: invokeinterface #29,  1    // InterfaceMethod Kotlin基础.pkg15_委托/MyInterface.myPrint:()V
       9: return
}

 */
