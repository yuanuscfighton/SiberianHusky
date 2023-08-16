package com.laioffer.第2部分_高级.第4章_线程中断.demo2;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: 演示: InterruptedException异常
 * Created by 春夏秋冬在中南 on 2023/8/13 14:28
 */
public class Demo424 {

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
        at com.laioffer.第2部分_高级.第4章_线程中断.demo2.Demo424$1.run(Demo424.java:24)
        at java.base/java.lang.Thread.run(Thread.java:834)
      thread1...
      thread1...
      thread1...
      ... ... ... ← 程序一直没有停止


小结:
      程序停止不了的原因是:
      👉🏻 如果线程处于被阻塞状态(例如，sleep、wait、join等状态)，
        在别的线程中调用当前线程对象的interrupt()方法时，那么线程将立即退出阻塞状态，并抛出InterruptedException异常

   */
}
