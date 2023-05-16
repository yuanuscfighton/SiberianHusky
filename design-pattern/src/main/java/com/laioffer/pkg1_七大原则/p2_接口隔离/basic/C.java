package com.laioffer.pkg1_七大原则.p2_接口隔离.basic;

/**
 * C类通过接口Interface1 依赖(使用)B类，但是只会用到operation1、4、5方法
 */
public class C {
  
  public void depend1(Interface1 i) {
    i.operation1();
  }

  public void depend4(Interface1 i) {
    i.operation4();
  }

  public void depend5(Interface1 i) {
    i.operation5();
  }
}
