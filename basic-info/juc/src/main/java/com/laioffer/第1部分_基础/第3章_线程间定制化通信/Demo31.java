package com.laioffer.第1部分_基础.第3章_线程间定制化通信;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类的描述: 线程间定制化通信
 * Created by 春夏秋冬在中南 on 2023/9/17 21:47
 */
public class Demo31 {
}

class ShareResource {
  // 定义标志位
  private int flag = 1; // 1-AA，2-BB，3-CC

  // 创建Lock锁
  private final Lock lock = new ReentrantLock();

  // 创建3个Condition
  private final Condition c1 = lock.newCondition();
  private final Condition c2 = lock.newCondition();
  private final Condition c3 = lock.newCondition();

  public void print5(int loop) throws InterruptedException {
    // 上锁
    lock.lock();
    try {
      // 判断
      while (flag != 1) {
        // 等待
        c1.await();
      }
      for (int i = 0; i < 5; i++) {
        System.out.println(Thread.currentThread().getName() + "::" + i + "... 轮次=" + loop);
      }

      // 通知
      flag = 2; // 修改标志位 为2
      c2.signal(); // 通知BB线程
    } finally {
      // 释放锁
      lock.unlock();
    }

  }

  public void print10(int loop) throws InterruptedException {
    // 上锁
    lock.lock();
    try {
      // 判断
      while (flag != 2) {
        // 等待
        c2.await();
      }
      for (int i = 0; i < 10; i++) {
        System.out.println(Thread.currentThread().getName() + "::" + i + "... 轮次=" + loop);
      }

      // 通知
      flag = 3; // 修改标志位 为3
      c3.signal(); // 通知CC线程
    } finally {
      // 释放锁
      lock.unlock();
    }

  }

  public void print15(int loop) throws InterruptedException {
    // 上锁
    lock.lock();
    try {
      // 判断
      while (flag != 3) {
        // 等待
        c3.await();
      }
      for (int i = 0; i < 15; i++) {
        System.out.println(Thread.currentThread().getName() + "::" + i + "... 轮次=" + loop);
      }

      // 通知
      flag = 1; // 修改标志位 为1
      c1.signal(); // 通知AA线程
    } finally {
      // 释放锁
      lock.unlock();
    }

  }
}
