package com.laioffer.pkg3_工厂模式.l2_工厂方法.pizza;

public class LDCheesePizza extends Pizza {

  @Override
  public void prepare() {
    setName("伦敦奶酪披萨");
    System.out.println("给伦敦奶酪披萨准备原材料");
  }
}
