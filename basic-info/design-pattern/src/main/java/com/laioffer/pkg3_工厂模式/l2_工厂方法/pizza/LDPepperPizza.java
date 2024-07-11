package com.laioffer.pkg3_工厂模式.l2_工厂方法.pizza;

public class LDPepperPizza extends Pizza {

  @Override
  public void prepare() {
    setName("");
    System.out.println("给伦敦胡椒披萨准备原材料");
  }
}
