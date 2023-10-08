package com.laioffer.第1部分_基础.第5章_多线程锁.demo2_可重入锁;

/**
 * 类的描述: synchronized 可重入锁 or 递归锁
 * Created by 春夏秋冬在中南 on 2023/10/7 22:46
 */
public class ReenterSyncDemo {

  public static void main(String[] args) {
    Object o = new Object();

    new Thread(() -> {
      synchronized (o) {
        System.out.println(Thread.currentThread().getName() + "\t" + "外层锁🔐");

        synchronized (o) {
          System.out.println(Thread.currentThread().getName() + "\t" + "中层锁🔐");

          synchronized (o) {
            System.out.println(Thread.currentThread().getName() + "\t" + "内层锁🔐");
          }
        }
      }
    }, "线程1").start();
  }
}

/*
  输出结果：
    线程1	外层锁🔐
    线程1	中层锁🔐
    线程1	内层锁🔐
 */