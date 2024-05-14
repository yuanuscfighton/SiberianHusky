package com.laioffer.pkg8_工厂模式.l1_简单工厂.pizza;

public class PepperPizza extends Pizza {

  @Override
  public void prepare() {
    System.out.println(" 给 胡椒披萨 准备原材料");
  }
}
