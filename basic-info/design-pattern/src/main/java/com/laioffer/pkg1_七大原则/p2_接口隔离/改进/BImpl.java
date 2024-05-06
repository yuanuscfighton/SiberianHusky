package com.laioffer.pkg1_七大原则.p2_接口隔离.改进;

public class BImpl implements InterfaceA, InterfaceB {
  public void operation1() {
    System.out.println("B实现了operation1");
  }
  
  public void operation2() {
    System.out.println("B实现了operation2");
  }
  
  public void operation3() {
    System.out.println("B实现了operation3");
  }
  
}
