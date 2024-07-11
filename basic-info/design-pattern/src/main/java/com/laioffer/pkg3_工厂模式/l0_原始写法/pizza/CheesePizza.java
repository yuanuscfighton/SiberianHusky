package com.laioffer.pkg3_工厂模式.l0_原始写法.pizza;

public class CheesePizza extends Pizza {

  @Override
  public void prepare() {
    System.out.println(" 给 奶酪披萨 准备原材料");
  }

}
