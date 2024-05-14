package com.laioffer.pkg8_工厂模式.l2_工厂方法.order;

import com.laioffer.pkg8_工厂模式.l2_工厂方法.pizza.LDCheesePizza;
import com.laioffer.pkg8_工厂模式.l2_工厂方法.pizza.LDPepperPizza;
import com.laioffer.pkg8_工厂模式.l2_工厂方法.pizza.Pizza;

public class LDOrderPizza extends OrderPizza {

  @Override
  Pizza createPizza(String orderType) {
    Pizza pizza = null;
    if (orderType.equals("cheese")) {
      pizza = new LDCheesePizza();
    } else if (orderType.equals("pepper")) {
      pizza = new LDPepperPizza();
    }
    return pizza;
  }

}
