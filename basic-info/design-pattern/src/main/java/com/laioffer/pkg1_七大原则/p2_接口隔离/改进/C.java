package com.laioffer.pkg1_七大原则.p2_接口隔离.改进;

public class C {
  public void depend1(InterfaceA i) {
    i.operation1();
  }
  
  public void depend4(InterfaceC i) {
    i.operation4();
  }
  
  public void depend5(InterfaceC i) {
    i.operation5();
  }
}
