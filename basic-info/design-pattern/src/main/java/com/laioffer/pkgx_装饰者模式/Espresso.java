package com.laioffer.pkgx_装饰者模式;

// 单品咖啡，意大利咖啡
public class Espresso extends Coffee {

  // 在创建这个咖啡的时候，就有了描述和价格，所以在构造器里调用 setDescription 和 setPrice 方法
  public Espresso() {
    setDescription(" 意大利🇮🇹咖啡☕️ ");
    setPrice(6.0f);
  }
}
