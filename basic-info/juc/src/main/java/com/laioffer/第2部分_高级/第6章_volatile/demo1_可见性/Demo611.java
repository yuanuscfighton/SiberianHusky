package com.laioffer.第2部分_高级.第6章_volatile.demo1_可见性;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: volatile可见性
 * Created by 春夏秋冬在中南 on 2023/8/21 23:12
 */
public class Demo611 {

  static boolean flag = true;

  public static void main(String[] args) {
    new Thread(() -> {
      System.out.println(Thread.currentThread().getName() + "\t [1]");
      while (flag) {

      }
      System.out.println(Thread.currentThread().getName() + "\t flag被设置为false，程序停止");
    }, "线程1").start();

    // 暂停几毫秒线程
    try {
      TimeUnit.MILLISECONDS.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    flag = false;
    System.out.println(Thread.currentThread().getName() + "\t 修改flag变量值为false");
  }
}

/*
打印：
    线程1	 [1]
    main	 修改flag变量值为false
    ... ... ← 程序没有停止

main线程修改flag为false后，没有刷新回主内存，线程1没有收到通知

 */