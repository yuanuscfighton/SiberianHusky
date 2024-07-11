package com.laioffer.pkg2_单例模式.l7_静态内部类;

/**
 * 类描述: 静态内部类
 * <p>
 * created by 西伯利亚哈士奇 on 2024/6/15 23:29
 */
public class SingletonClient7 {
  public static void main(String[] args) {

  }
}

class Singleton {
  private Singleton() {
  }

  private static class SingletonInstance {
    private static final Singleton INSTANCE = new Singleton();

    public static Singleton getInstance() {
      return SingletonInstance.INSTANCE;
    }
  }
}
