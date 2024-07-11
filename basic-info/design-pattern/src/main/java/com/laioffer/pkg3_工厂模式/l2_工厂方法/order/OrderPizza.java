package com.laioffer.pkg3_工厂模式.l2_工厂方法.order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.laioffer.pkg3_工厂模式.l2_工厂方法.pizza.Pizza;

public abstract class OrderPizza {

  abstract Pizza createPizza(String orderType);

  public OrderPizza() {
    Pizza pizza;
    String orderType;
    while (true) {
      orderType = getType();
      pizza = createPizza(orderType); // 抽象方法，由工厂子类完成
      pizza.prepare();
      pizza.bake();
      pizza.cut();
      pizza.box();
    }
  }

  private String getType() {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("输入披萨类型: ");
      return in.readLine();
    } catch (IOException e) {
      e.printStackTrace();
      return "";
    }
  }

}
