package com.laioffer.pkg10_外观模式;

public class DVDPlayer {

  // 使用单例模式，饿汉式
  private static final DVDPlayer sInstance = new DVDPlayer();

  public static DVDPlayer getInstance() {
    return sInstance;
  }

  public void on() {
    System.out.println(" DVD 打开了 ");
  }

  public void off() {
    System.out.println(" DVD 关闭了 ");
  }

  public void play() {
    System.out.println(" DVD 正在播放 ");
  }

  public void pause() {
    System.out.println(" DVD 暂停了 ");
  }
}
