package com.laioffer.pkg7_桥接模式;

/**
 * 抽象类：手机样式和品牌都在 Phone 里组装
 */
public abstract class Phone {

  // 组合品牌
  private final Brand mBrand;

  public Phone(Brand brand) {
    mBrand = brand;
  }

  protected void open() {
    // 运行时，brand 是哪个，就调用那个品牌的 open() 方法，例如 XiaoMi / Vivo
    mBrand.open();
  }

  protected void close() {
    mBrand.close();
  }

  protected void call() {
    mBrand.call();
  }

}
