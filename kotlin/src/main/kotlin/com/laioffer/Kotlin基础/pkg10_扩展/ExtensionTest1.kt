package Kotlin基础.pkg10_扩展


/**
 * 2.扩展函数的解析是静态分发的，而不是动态的，i.e. 不支持多态，调用只取决于对象的声明类型
 * 3.调用是由对象声明类型所决定的，而不是由对象的实际类型决定
 *
 * 声明的时候是什么类型，运行的时候就是什么类型
 */

open class AA

class BB : AA()

// 给AA类扩展出一个方法a
fun AA.a() = "[AA] aa"

// 给BB类扩展出一个方法a
fun BB.a() = "[BB] bb"

// 入参：声明了AA类型的引用。不管调用的地方传入哪个子类的实例，最终调用的一定是AA()中的a()方法。
// 根本原因是，扩展本身并不会真正修改原有的类。
fun myPrint(aa: AA) {
  println(aa.a())
}


// 扩展函数的解析是静态分发的，而不是动态的，即不支持多态，调用只取决于对象的声明类型
// 调用是由对象声明类型所决定的，而不是由对象的实际类型决定
fun main() {

  myPrint(AA()) // "aa"

  myPrint(BB()) // "aa"
}

/** ********** Java中的多态 ********** */
/*

class Animal {
  void a();
}

class Dog extends Animal {
  void a();
}

class Test {
  void test(Animal animal) {
    // 哪个类中的a()被调用，取决于入参animal实际指向的类型，
    // i.e. 如果在运行期过程中，传进来的是Animal实例，那么调用的就是Animal类中的a()方法，
    // 如果传入的是Dog实例，那么调用的就是Dog类中的a()方法
    // ==> 这里就是动态解析
    animal.a();
  }
}

*/
