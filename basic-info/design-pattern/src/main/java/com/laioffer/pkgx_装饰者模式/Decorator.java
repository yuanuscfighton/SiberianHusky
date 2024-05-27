package com.laioffer.pkgx_装饰者模式;

public class Decorator extends Drink {
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
    // mObj.getDescription() 被装饰者的信息
    return super.mDescription + " " + getPrice() + " && " + mObj.getDescription();
  }


}
