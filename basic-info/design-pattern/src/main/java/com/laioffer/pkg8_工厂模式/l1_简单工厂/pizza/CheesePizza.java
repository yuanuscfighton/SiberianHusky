package com.laioffer.pkg8_工厂模式.l1_简单工厂.pizza;

public class CheesePizza extends Pizza {

  @Override
  public void prepare() {
    System.out.println(" 给 奶酪披萨 准备原材料");
  }

}
