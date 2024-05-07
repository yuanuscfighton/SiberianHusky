package com.laioffer.pkg1_七大原则.p4_里氏替换.origin;

public class Liskov {

  public static void main(String[] args) {
    A a = new A();
    System.out.println("11-3=" + a.foo(11, 3)); // 11-3=8
    System.out.println("1-8=" + a.foo(1, 8)); // 1-8=-7

    System.out.println("-----------------------");
    B b = new B();
    System.out.println("11-3=" + b.foo(11, 3)); // 11-3=14
    System.out.println("1-8=" + b.foo(1, 8)); // 1-8=9
    System.out.println("11+3+9=" + b.func2(11, 3)); // 11+3+9=23
  }
}

class A {
  public int foo(int num1, int num2) {
    return num1 - num2;
  }
}

// 类B继承类A
// 类B希望完成两个数想加，然后和9求和
class B extends A {

  // 这里重写可能是无意识的，但本意是想做加法
  @Override
  public int foo(int a, int b) {
    return a + b;
  }

  public int func2(int a, int b) {
    return foo(a, b) + 9;
  }
}

/*
    问题
      我们发现原来运行正常的减法功能发生了错误，原因是类B无意中重写了父类的方法，造成原有功能出现错误，在实际编程过程中，我们常常会通过父类的方法完成新的功能，
        这样写起来虽然简单，但整个继承体系的复用性会比较差，特别是运行多态比较频繁的时候

    解决方案
      原来的父类和子类都继承一个更通俗的基类，原有的继承关系去掉，采用依赖、聚合、组合等关系代替
 */
