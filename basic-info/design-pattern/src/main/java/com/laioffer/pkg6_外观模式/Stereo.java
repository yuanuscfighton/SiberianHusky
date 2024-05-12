package com.laioffer.pkg6_外观模式;

// 立体声
public class Stereo {

  private static final Stereo sInstance = new Stereo();

  public static Stereo getInstance() {
    return sInstance;
  }

  public void on() {
    System.out.println(" 打开立体声音 ");
  }

  public void off() {
    System.out.println(" 关闭立体声音 ");
  }

  public void up() {
    System.out.println(" 调大立体声音 ");
  }
}
