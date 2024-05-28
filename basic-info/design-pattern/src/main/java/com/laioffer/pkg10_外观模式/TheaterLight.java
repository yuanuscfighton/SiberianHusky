package com.laioffer.pkg10_外观模式;

// 灯光
public class TheaterLight {

  private static final TheaterLight sInstance = new TheaterLight();

  public static TheaterLight getInstance() {
    return sInstance;
  }

  public void on() {
    System.out.println(" 打开灯光 ");
  }

  public void off() {
    System.out.println(" 关闭灯光 ");
  }

  public void dim() {
    System.out.println(" 调暗灯光 ");
  }

  public void bright() {
    System.out.println(" 调亮灯光 ");
  }
}
