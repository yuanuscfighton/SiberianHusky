package com.laioffer.pkg3_工厂模式.l0_原始写法.order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.laioffer.pkg3_工厂模式.l0_原始写法.pizza.CheesePizza;
import com.laioffer.pkg3_工厂模式.l0_原始写法.pizza.GreekPizza;
import com.laioffer.pkg3_工厂模式.l0_原始写法.pizza.PepperPizza;
import com.laioffer.pkg3_工厂模式.l0_原始写法.pizza.Pizza;


// 订购披萨🍕
public class OrderPizza {

  public OrderPizza() {
    while (true) {
      Pizza pizza;
      String orderType = getType(); // 订购披萨的类型

      switch (orderType) {
        case "greek":
          pizza = new GreekPizza();
          pizza.setName("希腊披萨");
          break;
        case "cheese":
          pizza = new CheesePizza();
          pizza.setName("奶酪披萨");
          break;
        case "pepper":
          pizza = new PepperPizza();
          pizza.setName("胡椒披萨");
          break;
        default:
          return;
      }

      pizza.prepare();
      pizza.bake();
      pizza.cut();
      pizza.box();
    }
  }

  // 写一个方法，可以获取客户希望订购的披萨类型
  private String getType() {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("输入订购的披萨类型: ");
      return in.readLine();
    } catch (IOException e) {
      e.printStackTrace();
      return "";
    }
  }

}
