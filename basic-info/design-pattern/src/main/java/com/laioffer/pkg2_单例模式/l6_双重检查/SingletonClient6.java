package com.laioffer.pkg2_单例模式.l6_双重检查;

/**
 * 类描述: 双重检查
 * <p>
 * created by 西伯利亚哈士奇 on 2024/6/15 23:18
 */
public class SingletonClient6 {
  public static void main(String[] args) {

  }
}

class Singleton {
  private static volatile Singleton instance;

  private Singleton() {}

  // 提供一个静态的公有方法，加入了双重价差代码，解决线程安全问题，同时也解决懒加载问题
  public static Singleton getInstance() {
    if (instance == null) {
      synchronized (Singleton.class) {
        if (instance == null) {
          instance = new Singleton();
        }
      }
    }
    return instance;
  }
}
