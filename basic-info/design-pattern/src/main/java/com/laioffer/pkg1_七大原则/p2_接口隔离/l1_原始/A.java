package com.laioffer.pkg1_七大原则.p2_接口隔离.l1_原始;

/**
 * A 类通过接口 Interface1 依赖(使用) B 类，但是只会用到 operation1、operation2、operation3 方法
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

/*
  分析
      类 A 通过接口 Interface1 依赖类 B，类 C 通过接口 Interface1 依赖 D。
      如果接口 Interface1 对于类 A 和 类 C 来说不是最小接口，那么类 B 和类 D 必须去实现它们不需要的方法

  接口隔离原则应该这样处理：
      将接口 Interface1 拆分为独立的几个接口，类 A 和类 C 分别与它们需要的接口建立依赖关系，也就是采用接口隔离原则
 */
