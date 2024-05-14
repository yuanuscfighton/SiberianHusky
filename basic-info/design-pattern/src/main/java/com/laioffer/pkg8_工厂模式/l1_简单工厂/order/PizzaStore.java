package com.laioffer.pkg8_工厂模式.l1_简单工厂.order;

public class PizzaStore {

  public static void main(String[] args) {
    // 使用简单工厂模式
    new OrderPizza(new SimpleFactory());

    // 使用静态工厂模式
    new OrderPizza2();
  }

}
