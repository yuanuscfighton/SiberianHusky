package com.laioffer.pkg8_装饰者模式;

/**
 * 将单品咖啡的共同点提取到这里
 */
public class Coffee extends Drink {

  @Override
  public float cost() {
    return super.getPrice();
  }
}
