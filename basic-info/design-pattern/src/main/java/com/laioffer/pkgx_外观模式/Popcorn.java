package com.laioffer.pkgx_外观模式;

// 爆米花
public class Popcorn {

  private static final Popcorn sInstance = new Popcorn();

  public static Popcorn getInstance() {
    return sInstance;
  }

  public void on() {
    System.out.println(" 开始制作爆米花 ");
  }

  public void off() {
    System.out.println(" 关闭爆米花机器 ");
  }

  public void pop() {
    System.out.println(" 正在出爆米花 ");
  }
}
