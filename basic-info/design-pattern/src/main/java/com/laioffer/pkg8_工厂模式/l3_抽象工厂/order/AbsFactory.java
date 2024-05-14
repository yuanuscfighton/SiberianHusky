package com.laioffer.pkg8_工厂模式.l3_抽象工厂.order;


import com.laioffer.pkg8_工厂模式.l3_抽象工厂.pizza.Pizza;

// 一个抽象工厂模式的抽象层
public interface AbsFactory {

  // 让下面的工厂子类来具体实现
  Pizza createPizza(String orderType);
}
