package com.laioffer.demo3;

/**
 * 类的描述: 可重入锁
 * Created by 春夏秋冬在中南 on 2023/6/4 22:43
 */
public class SyncLockDemo {
  public static void main(String[] args) {
    Object o = new Object();
    new Thread(() -> {
      synchronized (o) {
        System.out.println(Thread.currentThread().getName() + "外层");

        synchronized (o) {
          System.out.println(Thread.currentThread().getName() + "中层");

          synchronized (o) {
            System.out.println(Thread.currentThread().getName() + "内层");
          }
        }
      }
    }, "t1").start();
  }
}
