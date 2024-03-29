package com.laioffer.第2部分_高级.第3章_锁.demo4_可重入锁;

/**
 * 类的描述: 可重入锁: 同步方法
 * Created by 春夏秋冬在中南 on 2023/7/18 22:46
 */
public class Demo342 {

  public static void main(String[] args) {
    Demo342 demo342 = new Demo342();
    new Thread(demo342::foo1, "线程2").start();
  }

  public synchronized void foo1() {
    System.out.println(Thread.currentThread().getName() + "\t 进入foo1");
    foo2();
    System.out.println(Thread.currentThread().getName() + "\t 退出foo1");
  }

  public synchronized void foo2() {
    System.out.println(Thread.currentThread().getName() + "\t 进入foo2");
    foo3();
  }

  public synchronized void foo3() {
    System.out.println(Thread.currentThread().getName() + "\t 进入foo3");
  }
}
