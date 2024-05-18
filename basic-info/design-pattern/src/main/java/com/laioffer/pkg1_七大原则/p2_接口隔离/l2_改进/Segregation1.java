package com.laioffer.pkg1_七大原则.p2_接口隔离.l2_改进;

public class Segregation1 {

  public static void main(String[] args) {
    A a = new A();
    a.depend1(new BImpl()); // A 类通过接口依赖 B 类
    a.depend2(new BImpl());
    a.depend3(new BImpl());


    C c = new C();
    c.depend1(new DImpl()); // C 类通过接口去依赖（使用）D 类
    c.depend4(new DImpl());
    c.depend5(new DImpl());
  }

}

