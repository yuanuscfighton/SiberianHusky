package com.laioffer.pkg1_七大原则.p2_接口隔离.l1_原始;

/**
 * C 类通过接口 Interface1 依赖(即，使用) D 类，但是只会用到 operation1、operation4、operation5 方法
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
