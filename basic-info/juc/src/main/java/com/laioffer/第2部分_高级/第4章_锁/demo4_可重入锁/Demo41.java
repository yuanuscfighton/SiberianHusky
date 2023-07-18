package com.laioffer.第2部分_高级.第4章_锁.demo4_可重入锁;

/**
 * 类的描述: 可重入锁: 同步代码块
 * Created by 春夏秋冬在中南 on 2023/7/17 22:56
 */
public class Demo41 {

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
