package com.laioffer.pkg10_外观模式;

// 投影仪
public class Projector {

  private static final Projector sInstance = new Projector();

  public static Projector getInstance() {
    return sInstance;
  }

  public void on() {
    System.out.println(" 打开投影仪 ");
  }

  public void off() {
    System.out.println(" 关闭投影仪 ");
  }

  public void focus() {
    System.out.println(" 投影仪正在聚焦 ");
  }

  //...
}
