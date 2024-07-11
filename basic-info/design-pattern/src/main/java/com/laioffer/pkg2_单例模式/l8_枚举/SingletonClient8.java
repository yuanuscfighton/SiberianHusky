package com.laioffer.pkg2_单例模式.l8_枚举;

/**
 * 类描述: 枚举
 * <p>
 * created by 西伯利亚哈士奇 on 2024/6/15 23:35
 */
public class SingletonClient8 {
  public static void main(String[] args) {
    Singleton ins = Singleton.INSTANCE;
    Singleton ins2 = Singleton.INSTANCE;

    System.out.println(ins == ins2);
    System.out.println(ins.hashCode());
    System.out.println(ins2.hashCode());
  }
}

// 使用枚举可以实现单例
enum Singleton {
  INSTANCE;

  public void say() {
    System.out.println("hello");
  }
}