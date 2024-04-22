package com.laioffer.pkg1_七大原则.p2_接口隔离.basic;

/**
 * A类通过接口Interface1 依赖(使用)B类，但是只会用到operation1、2、3方法
 */
public class A {
  
  public void depend1(Interface1 i) {
    i.operation1();
  }

  public void depend2(Interface1 i) {
    i.operation2();
  }

  public void depend3(Interface1 i) {
    i.operation3();
  }
}
