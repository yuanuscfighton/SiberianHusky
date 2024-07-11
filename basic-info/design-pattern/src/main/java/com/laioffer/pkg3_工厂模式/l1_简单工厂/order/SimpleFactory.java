package com.laioffer.pkg3_工厂模式.l1_简单工厂.order;

import com.laioffer.pkg3_工厂模式.l1_简单工厂.pizza.CheesePizza;
import com.laioffer.pkg3_工厂模式.l1_简单工厂.pizza.GreekPizza;
import com.laioffer.pkg3_工厂模式.l1_简单工厂.pizza.PepperPizza;
import com.laioffer.pkg3_工厂模式.l1_简单工厂.pizza.Pizza;

/**
 * 简单工厂类
 */
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

      // TODO: 新增 胡椒类型 pizza，只需要在 SimpleFactory 中修改代码即可。
      //  而使用简单工厂的 OrderPizza 不需要修改
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

}
