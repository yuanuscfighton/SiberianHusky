package com.laioffer.第2部分_高级.第4章_线程中断.demo2;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: 演示: InterruptedException异常
 * Created by 春夏秋冬在中南 on 2023/8/13 14:28
 */
public class Demo423 {

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
    thread1...
    thread1...
    thread1...
    thread1...
    thread1...
    thread1...
    thread1...
    thread1...
    thread1...
    thread1中断标志位=true


   */
}
