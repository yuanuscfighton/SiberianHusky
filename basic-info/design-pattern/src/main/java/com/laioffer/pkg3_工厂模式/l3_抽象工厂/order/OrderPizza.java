package com.laioffer.pkg3_工厂模式.l3_抽象工厂.order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.laioffer.pkg3_工厂模式.l3_抽象工厂.pizza.Pizza;

public class OrderPizza {

  // factory 可能是"北京工厂子类"，也可能是"伦敦工厂子类"
  public OrderPizza(AbsFactory factory) {
    setFactory(factory);
  }

  private void setFactory(AbsFactory factory) {
    Pizza pizza;
    String orderType;

    while (true) {
      orderType = getType();
      pizza = factory.createPizza(orderType);
      if (pizza != null) {
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
      } else {
        break;
      }
    }
  }

  private String getType() {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("输入披萨种类: ");
      return in.readLine();
    } catch (IOException e) {
      e.printStackTrace();
      return "";
    }
  }
}
