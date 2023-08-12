package com.laioffer.第2部分_高级.第4章_线程中断.demo2;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: 实例方法interrupt demo演示
 * Created by 春夏秋冬在中南 on 2023/8/12 01:33
 */
public class Demo422 {

  public static void main(String[] args) {
    Thread thread1 = new Thread(() -> {
      // 调用thread1.interrupt()方法后，仅仅是将中断标识位设置为true了，但不会打断for循环
      for (int i = 0; i < 100; i++) {
        System.out.println("当前i的值: " + i);
      }
      System.out.println("[1] t1线程调用interrupt()方法后的中断标识是: " + Thread.currentThread().isInterrupted());
    });
    thread1.start();

    System.out.println("t1线程默认的中断标识是: " + thread1.isInterrupted()); // 默认值是false

    // 暂停几毫秒线程
    try {
      TimeUnit.MILLISECONDS.sleep(2);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    thread1.interrupt(); // 此处将中断标识位置为true，所以下面打印出的结果就是true
    System.out.println("[2] t1线程调用interrupt()方法后的中断标识是: " + thread1.isInterrupted());

    // 延迟2秒后，thread1线程已经结束了，所以thread1已经是不活跃的线程了，因此下面👇🏻打印的结果是false
    try {
      TimeUnit.MILLISECONDS.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("[3] t1线程调用interrupt()方法后的中断标识是: " + thread1.isInterrupted());
  }
}

/*
打印结果:
    t1线程默认的中断标识是: false
    当前i的值: 0
    当前i的值: 1
    当前i的值: 2
    ... ... ...
    当前i的值: 85
    当前i的值: 86
    当前i的值: 87
    [2] t1线程调用interrupt()方法后的中断标识是: true
    当前i的值: 88
    当前i的值: 89
    当前i的值: 90
    ... ... ...
    当前i的值: 97
    当前i的值: 98
    当前i的值: 99
    [1] t1线程调用interrupt()方法后的中断标识是: true
    [3] t1线程调用interrupt()方法后的中断标识是: false

  小结:
      实例方法interrupt()仅仅是设置线程的中断状态位为true，不会停止线程
 */
