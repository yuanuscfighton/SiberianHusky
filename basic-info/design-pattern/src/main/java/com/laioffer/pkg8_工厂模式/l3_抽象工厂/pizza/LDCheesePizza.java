package com.laioffer.pkg8_工厂模式.l3_抽象工厂.pizza;

public class LDCheesePizza extends Pizza {

  @Override
  public void prepare() {
    setName("伦敦奶酪披萨");
    System.out.println("给伦敦奶酪披萨准备原材料");
  }
}
