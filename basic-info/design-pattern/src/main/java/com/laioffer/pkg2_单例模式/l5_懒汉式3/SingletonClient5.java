package com.laioffer.pkg2_单例模式.l5_懒汉式3;

/**
 * 类描述: 懒汉式-线程安全，同步代码块
 * <p>
 * created by 西伯利亚哈士奇 on 2024/6/14 23:08
 */
public class SingletonClient5 {
  public static void main(String[] args) {

  }
}

class Singleton {
  private static Singleton instance;

  private Singleton() {
  }

  public static Singleton getInstance() {
    if (instance == null) {
      // 不能使用该方法，会有线程安全问题。
      // 假如一个线程进入了 if (instance == null) 判断语句块，还未来得及往下执行，
      // 另一个线程也通过了这个判断语句。这时候就会产生多个实例
      synchronized (Singleton.class) {
        instance = new Singleton();
      }
    }
    return instance;
  }
}
