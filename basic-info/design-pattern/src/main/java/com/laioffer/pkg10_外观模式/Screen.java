package com.laioffer.pkg10_外观模式;

public class Screen {

  private static final Screen sInstance = new Screen();

  public static Screen getInstance() {
    return sInstance;
  }

  public void up() {
    System.out.println(" Screen up ");
  }

  public void down() {
    System.out.println(" Screen down ");
  }

}
