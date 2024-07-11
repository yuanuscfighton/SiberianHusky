package com.laioffer.pkg8_装饰者模式;

// 调味品
public class Decorator extends Drink {

  // 调料中 组合单品咖啡☕️
  private final Drink mObj;

  public Decorator(Drink obj) {
    mObj = obj; // 体现组合的关系。即，在创建 Decorator 的时候，把单品咖啡传进来
  }

  @Override
  public float cost() {
    // super.getPrice() 调料的价格
    // obj.cost() 单品咖啡的价格
    return super.getPrice() + mObj.cost();
  }

  @Override
  public String getDescription() {
    // mObj.getDescription() 被装饰者的信息，即，单品咖啡的描述
    return super.mDescription + " " + super.getPrice() + " && " + mObj.getDescription();
  }


}
