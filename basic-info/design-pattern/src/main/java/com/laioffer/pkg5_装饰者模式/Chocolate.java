package com.laioffer.pkg5_装饰者模式;

// 具体的 Decorator。即，调味品的价格
public class Chocolate extends Decorator {

  public Chocolate(Drink obj) {
    super(obj);
    setDescription(" 巧克力🍫 ");
    setPrice(3.0f); // 调味品的价格
  }
}
