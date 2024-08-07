package com.laioffer.pkg8_装饰者模式;

public abstract class Drink {

  public String mDescription; // 描述
  private float mPrice = 0.0f;

  public String getDescription() {
    return mDescription;
  }

  public void setDescription(String description) {
    mDescription = description;
  }

  public float getPrice() {
    return mPrice;
  }

  public void setPrice(float price) {
    mPrice = price;
  }

  // 计算费用的抽象方法 ← 子类来实现
  public abstract float cost();

}
