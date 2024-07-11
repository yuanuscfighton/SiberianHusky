package com.laioffer.pkg2_单例模式.l3_懒汉式1;

/**
 * 类描述: 懒汉式-线程不安全
 * <p>
 * created by 西伯利亚哈士奇 on 2024/6/14 23:08
 */
public class SingletonClient3 {
  public static void main(String[] args) {

  }
}

class Singleton {
  private static Singleton instance;

  private Singleton() {}

  // 提供一个静态的公有方法，当使用到该方法的时候，才创建 instance，即，懒汉式
  public static Singleton getInstance() {
    if (instance == null) {
      instance = new Singleton();
    }
    return instance;
  }
}
