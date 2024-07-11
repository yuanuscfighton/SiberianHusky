package com.laioffer.pkg1_七大原则.p2_接口隔离.l2_改进;

public class A {

  // A类通过接口 InterfaceA 和 InterfaceB 依赖（使用）B 类
  public void depend1(InterfaceA i) {
    i.operation1();
  }

  public void depend2(InterfaceB i) {
    i.operation2();
  }

  public void depend3(InterfaceB i) {
    i.operation3();
  }
}
