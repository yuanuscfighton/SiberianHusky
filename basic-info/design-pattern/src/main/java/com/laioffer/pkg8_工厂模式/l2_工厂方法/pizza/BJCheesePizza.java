package com.laioffer.pkg8_工厂模式.l2_工厂方法.pizza;

public class BJCheesePizza extends Pizza {

  @Override
  public void prepare() {
    setName("北京奶酪披萨");
    System.out.println("给北京奶酪披萨准备原材料");
  }

}
