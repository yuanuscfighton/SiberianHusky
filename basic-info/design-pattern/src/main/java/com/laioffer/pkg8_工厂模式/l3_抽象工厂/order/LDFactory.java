package com.laioffer.pkg8_工厂模式.l3_抽象工厂.order;

import com.laioffer.pkg8_工厂模式.l3_抽象工厂.pizza.LDCheesePizza;
import com.laioffer.pkg8_工厂模式.l3_抽象工厂.pizza.LDPepperPizza;
import com.laioffer.pkg8_工厂模式.l3_抽象工厂.pizza.Pizza;

// 工厂子类
public class LDFactory implements AbsFactory {

  @Override
  public Pizza createPizza(String orderType) {
    System.out.println("");
    Pizza pizza = null;
    if (orderType.equals("cheese")) {
      pizza = new LDCheesePizza();
    } else if (orderType.equals("pepper")) {
      pizza = new LDPepperPizza();
    }
    return pizza;
  }

}
