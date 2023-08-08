package com.laioffer.第2部分_高级.第3章_锁.demo4_可重入锁;

/**
 * 类的描述: 可重入锁: 同步代码块
 * Created by 春夏秋冬在中南 on 2023/7/17 22:56
 */
public class Demo341 {

  public static void main(String[] args) {
    final Object object = new Object();

    new Thread(() -> {
      synchronized (object) {
        System.out.println(Thread.currentThread().getName() + "\t 外层调用");
        synchronized (object) {
          System.out.println(Thread.currentThread().getName() + "\t 中层调用");
          synchronized (object) {
            System.out.println(Thread.currentThread().getName() + "\t 内层调用");
          }
        }
      }
    }, "线程1").start();
  }
}

/*
线程1	 外层调用
线程1	 中层调用
线程1	 内层调用

小结:
    在一个synchronized修饰的方法或代码块内部调用本类的其它synchronized修饰的方法或者代码块时，是永远可以得到锁的
 */