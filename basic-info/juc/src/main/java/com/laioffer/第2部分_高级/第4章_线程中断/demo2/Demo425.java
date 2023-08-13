package com.laioffer.第2部分_高级.第4章_线程中断.demo2;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: 为什么需要在InterruptedException异常的地方再调用一次interrupt()方法
 * Created by 春夏秋冬在中南 on 2023/8/13 14:28
 */
public class Demo425 {

  public static void main(String[] args) {
    Thread thread1 = new Thread(new Runnable() {
      @Override
      public void run() {
        while (true) {
          if (Thread.currentThread().isInterrupted()) {
            System.out.println(Thread.currentThread().getName() +
                "中断标志位=" + Thread.currentThread().isInterrupted());
            break;
          }

          // 暂停几毫秒线程
          try {
            Thread.sleep(200);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
          }
          System.out.println("thread1...");
        }
      }
    }, "thread1");
    thread1.start();

    // 暂停几毫秒线程
    try {
      TimeUnit.MILLISECONDS.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    Thread thread2 = new Thread(new Runnable() {
      @Override
      public void run() {
        thread1.interrupt();
      }
    }, "thread2");
    thread2.start();
  }

  /*
      thread1...
      thread1...
      thread1...
      thread1...
      java.lang.InterruptedException: sleep interrupted
        at java.base/java.lang.Thread.sleep(Native Method)
        at com.laioffer.第2部分_高级.第4章_线程中断.demo2.Demo425$1.run(Demo425.java:24)
        at java.base/java.lang.Thread.run(Thread.java:834)
      thread1...
      thread1中断标志位=true  ← 程序停止了


   */
}
