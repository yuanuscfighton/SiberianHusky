package com.laioffer.第2部分_高级.第4章_线程中断.demo2_wait_notify;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: Object中的wait和notify的异常情况1
 * Created by 春夏秋冬在中南 on 2023/7/25 07:04
 */
public class Demo522 {

  public static void main(String[] args) {
    Object objectLock = new Object();

    new Thread(() -> {
      System.out.println(Thread.currentThread().getName() + "\t [1]");
      try {
        objectLock.wait();
      } catch (Exception e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName() + "\t 被唤醒");
    }, "线程1").start();

    // 暂停几毫秒线程
    try {
      TimeUnit.MILLISECONDS.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    new Thread(() -> {
      objectLock.notify();
      System.out.println(Thread.currentThread().getName() + "\t 通知");
    }, "线程2").start();
  }
}

/*
会抛出异常:

线程1	 [1]
线程1	 被唤醒
java.lang.IllegalMonitorStateException
	at java.base/java.lang.Object.wait(Native Method)
	at java.base/java.lang.Object.wait(Object.java:328)
	at com.laioffer.第2部分_高级.第5章_线程中断.demo2_等待与唤醒.Demo522.lambda$main$0(Demo522.java:17)
	at java.base/java.lang.Thread.run(Thread.java:834)
Exception in thread "线程2" java.lang.IllegalMonitorStateException
	at java.base/java.lang.Object.notify(Native Method)
	at com.laioffer.第2部分_高级.第5章_线程中断.demo2_等待与唤醒.Demo522.lambda$main$1(Demo522.java:32)
	at java.base/java.lang.Thread.run(Thread.java:834)

 */