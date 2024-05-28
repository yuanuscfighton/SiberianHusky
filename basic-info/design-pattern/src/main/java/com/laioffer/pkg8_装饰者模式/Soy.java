package com.laioffer.pkg8_装饰者模式;

// 豆浆 调味品
public class Soy extends Decorator {

  public Soy(Drink obj) {
    super(obj);
    setDescription(" 豆浆 ");
    setPrice(1.5f);
  }

}
