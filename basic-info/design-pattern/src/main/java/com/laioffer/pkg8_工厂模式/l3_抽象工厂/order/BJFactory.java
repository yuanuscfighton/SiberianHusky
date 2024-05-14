package com.laioffer.pkg8_工厂模式.l3_抽象工厂.order;

import com.laioffer.pkg8_工厂模式.l3_抽象工厂.pizza.BJCheesePizza;
import com.laioffer.pkg8_工厂模式.l3_抽象工厂.pizza.BJPepperPizza;
import com.laioffer.pkg8_工厂模式.l3_抽象工厂.pizza.Pizza;

// 工厂子类
public class BJFactory implements AbsFactory {

  @Override
  public Pizza createPizza(String orderType) {
    System.out.println("");
    Pizza pizza = null;

    if (orderType.equals("cheese")) {
      pizza = new BJCheesePizza();
    } else if (orderType.equals("pepper")) {
      pizza = new BJPepperPizza();
    }
    return pizza;
  }

}
