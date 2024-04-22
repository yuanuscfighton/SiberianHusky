package com.laioffer.pkg1_七大原则.p2_接口隔离.改进;

public class A {

  // A类通过接口InterfaceA和InterfaceB依赖(使用)B类
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
