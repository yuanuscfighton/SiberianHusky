package com.laioffer.pkg1_七大原则.p2_接口隔离.l2_改进;

public class DImpl implements InterfaceA, InterfaceC {
  public void operation1() {
    System.out.println("D实现了operation1");
  }
  
  public void operation4() {
    System.out.println("D实现了operation4");
  }
  
  public void operation5() {
    System.out.println("D实现了operation5");
  }
}
