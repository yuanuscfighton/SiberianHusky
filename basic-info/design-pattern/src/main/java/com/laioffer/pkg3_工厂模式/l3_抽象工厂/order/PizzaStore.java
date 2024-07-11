package com.laioffer.pkg3_工厂模式.l3_抽象工厂.order;

public class PizzaStore {

  public static void main(String[] args) {
    new OrderPizza(new BJFactory());
    new OrderPizza(new LDFactory());
  }

}
