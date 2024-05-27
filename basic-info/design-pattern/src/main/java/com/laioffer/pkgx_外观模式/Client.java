package com.laioffer.pkgx_外观模式;

public class Client {

  public static void main(String[] args) {
    // 这里如果直接调用各个类的方法，很麻烦
    // 所以增加 HomeTheaterFacade 类
    HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade();
    homeTheaterFacade.ready();
    homeTheaterFacade.play();
    homeTheaterFacade.pause();
    homeTheaterFacade.end();
  }
}
