package com.laioffer.pkg2_单例模式.l2_饿汉式2;

/**
 * 类描述: 饿汉式-静态常量
 * <p>
 * created by 西伯利亚哈士奇 on 2024/6/14 22:35
 */
public class SingletonClient2 {
  public static void main(String[] args) {
    Singleton ins1 = Singleton.getInstance();
    Singleton ins2 = Singleton.getInstance();
    System.out.println(ins1 == ins2); // true
    System.out.println("ins1.hashCode = " + ins1.hashCode());
    System.out.println("ins2.hashCode = " + ins2.hashCode());

  }
}

class Singleton {

  // 1.构造器私有化，外部不能 new
  private Singleton() {

  }

  // 2.在本类内部创建对象实例，在类的加载的时候，就创建实例
  private final static Singleton instance;

  // 在静态代码块中，创建单例对象
  static {
    instance = new Singleton();
  }

  // 3.对外提供一个公有的静态方法，返回实例对象
  public static Singleton getInstance() {
    return instance;
  }
}
