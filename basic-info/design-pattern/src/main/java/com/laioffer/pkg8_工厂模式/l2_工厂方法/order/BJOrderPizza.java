package com.laioffer.pkg8_工厂模式.l2_工厂方法.order;

import com.laioffer.pkg8_工厂模式.l2_工厂方法.pizza.BJCheesePizza;
import com.laioffer.pkg8_工厂模式.l2_工厂方法.pizza.Pizza;
import com.laioffer.pkg8_工厂模式.l2_工厂方法.pizza.BJPepperPizza;


public class BJOrderPizza extends OrderPizza {

  @Override
  Pizza createPizza(String orderType) {
    Pizza pizza = null;
    if (orderType.equals("cheese")) {
      pizza = new BJCheesePizza();
    } else if (orderType.equals("pepper")) {
      pizza = new BJPepperPizza();
    }
    return pizza;
  }

}
