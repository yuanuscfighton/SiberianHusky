package com.laioffer.pkg3_工厂模式.l2_工厂方法.pizza;

public abstract class Pizza {

  protected String name;

  public abstract void prepare();


  public void bake() {
    System.out.println(name + " baking;");
  }

  public void cut() {
    System.out.println(name + " cutting;");
  }

  public void box() {
    System.out.println(name + " boxing;");
  }

  public void setName(String name) {
    this.name = name;
  }
}
