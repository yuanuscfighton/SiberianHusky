package com.laioffer.第2部分_高级.第6章_volatile.demo1_可见性;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: 添加volatile关键字后，程序正常结束
 * Created by 春夏秋冬在中南 on 2023/8/21 23:20
 */
public class Demo612 {

  static volatile boolean flag = true;

  public static void main(String[] args) {
    new Thread(() -> {
      System.out.println(Thread.currentThread().getName() + "\t [1]");
      while (flag) {

      }
      System.out.println(Thread.currentThread().getName() + "\t flag被设置为false，程序停止");
    }, "线程2").start();

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
打印
    线程2	 [1]
    main	 修改flag变量值为false
    线程2	 flag被设置为false，程序停止

小结：
  1. 不加volatile，没有可见性，程序无法停止
  2. 加了volatile，保证可见性，程序可以停止
 */
