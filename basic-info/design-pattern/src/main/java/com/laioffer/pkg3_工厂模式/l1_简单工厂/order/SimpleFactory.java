package com.laioffer.pkg3_工厂模式.l1_简单工厂.order;

import com.laioffer.pkg3_工厂模式.l1_简单工厂.pizza.GreekPizza;
import com.laioffer.pkg3_工厂模式.l1_简单工厂.pizza.Pizza;
import com.laioffer.pkg3_工厂模式.l1_简单工厂.pizza.CheesePizza;
import com.laioffer.pkg3_工厂模式.l1_简单工厂.pizza.PepperPizza;

// 简单工厂类
public class SimpleFactory {

  // 根据 orderType 返回相应的 Pizza 对象
  public Pizza createPizza(String orderType) {

    Pizza pizza = null;

    System.out.println("使用简单工厂模式");
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
    }

    return pizza;
  }

  // 简单工厂模式 也叫 静态工厂模式。即，加上 static
  public static Pizza createPizza2(String orderType) {

    Pizza pizza = null;

    System.out.println("");
    switch (orderType) {
      case "greek":
        pizza = new GreekPizza();
        pizza.setName(" ϣ������ ");
        break;
      case "cheese":
        pizza = new CheesePizza();
        pizza.setName(" �������� ");
        break;
      case "pepper":
        pizza = new PepperPizza();
        pizza.setName("��������");
        break;
    }

    return pizza;
  }

}
