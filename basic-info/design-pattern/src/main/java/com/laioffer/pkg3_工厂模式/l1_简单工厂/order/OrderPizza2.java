package com.laioffer.pkg3_工厂模式.l1_简单工厂.order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.laioffer.pkg3_工厂模式.l1_简单工厂.pizza.Pizza;


// 订购披萨🍕
public class OrderPizza2 {

  public OrderPizza2() {
    String orderType;
    Pizza pizza;

    while (true) {
      orderType = getType();
      pizza = SimpleFactory.createPizza2(orderType);

      if (pizza != null) {
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
      } else {
        System.out.println("没有这个类型的披萨");
        break;
      }
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
