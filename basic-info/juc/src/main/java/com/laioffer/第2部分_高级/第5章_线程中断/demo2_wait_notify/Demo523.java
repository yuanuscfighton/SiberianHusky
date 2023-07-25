package com.laioffer.第2部分_高级.第5章_线程中断.demo2_wait_notify;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: Object中的wait和notify异常情况2
 * Created by 春夏秋冬在中南 on 2023/7/25 07:07
 */
public class Demo523 {

  public static void main(String[] args) {
    Object objectLock = new Object();

    new Thread(() -> {
      synchronized (objectLock) {
        objectLock.notify();
        System.out.println(Thread.currentThread().getName() + "\t 通知");
      }
    }, "线程2").start();

    // 暂停几毫秒线程
    try {
      TimeUnit.MILLISECONDS.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

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

  }
}

/*
将notify放在wait之前，导致「程序无法执行，无法被唤醒」

线程2	 通知
线程1	 [1]

 */