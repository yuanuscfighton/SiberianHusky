package com.laioffer.pkg8_装饰者模式;

// 牛奶 调味品
public class Milk extends Decorator {

  public Milk(Drink obj) {
    super(obj);
    setDescription(" 牛奶🥛 ");
    setPrice(2.0f);
  }

}
