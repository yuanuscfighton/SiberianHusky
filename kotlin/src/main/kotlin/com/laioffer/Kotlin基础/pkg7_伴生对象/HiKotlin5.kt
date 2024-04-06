package Kotlin基础.pkg7_伴生对象

class HiKotlin5 {

  companion object {

    // JvmStatic注解对于程序上调用是没有影响的
    @JvmStatic
    fun foo() {

    }

    fun bar() {

    }
  }
}

fun main() {
  HiKotlin5.Companion.foo()
  HiKotlin5.foo()
  HiKotlin5.Companion.bar()
  HiKotlin5.bar()
}

/*
javap Kotlin学习.pkg7.HiKotlin5

public final class Kotlin学习.kotlin基础.pkg7_伴生对象.HiKotlin5 {
  public static final Kotlin学习.kotlin基础.pkg7_伴生对象.HiKotlin5$Companion Companion;
  public Kotlin学习.kotlin基础.pkg7_伴生对象.HiKotlin5();
  public static final void foo();
  static {};
}

 */
