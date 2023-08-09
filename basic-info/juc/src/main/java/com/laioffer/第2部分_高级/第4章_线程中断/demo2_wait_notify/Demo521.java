package com.laioffer.第2部分_高级.第4章_线程中断.demo2_wait_notify;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: Object中的wait和notify
 * Created by 春夏秋冬在中南 on 2023/7/25 07:00
 */
public class Demo521 {

  public static void main(String[] args) {
    Object objectLock = new Object();

    new Thread(() -> {
      synchronized (objectLock) {
        System.out.println(Thread.currentThread().getName() + "\t [1]");
        try {
          objectLock.wait();
        } catch (Exception e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t 被唤醒");
      }
    }, "线程1").start();

    // 暂停几毫秒线程
    try {
      TimeUnit.MILLISECONDS.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    new Thread(() -> {
      synchronized (objectLock) {
        objectLock.notify();
        System.out.println(Thread.currentThread().getName() + "\t 通知");
      }
    }, "线程2").start();
  }
}
