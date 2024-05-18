package com.laioffer.pkg3_工厂模式.l0_原始写法.pizza;

public abstract class Pizza {

  protected String mName;

  // 1.准备原材料。不同 Pizza 准备的材料不一样，所以做成抽象方法
  public abstract void prepare();

  // 2.烘焙
  public void bake() {
    System.out.println(mName + " baking;");
  }

  // 3.切割
  public void cut() {
    System.out.println(mName + " cutting;");
  }

  // 4.打包
  public void box() {
    System.out.println(mName + " boxing;");
  }

  public void setName(String name) {
    mName = name;
  }
}
