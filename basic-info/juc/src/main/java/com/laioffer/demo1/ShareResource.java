package com.laioffer.demo1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类的描述: 线程间定制化通信
 * Created by 春夏秋冬在中南 on 2023/6/3 22:41
 */

// 第1步:创建资源类
public class ShareResource {
  // 定义标志位
  private int flag = 1;
  // 创建Lock锁
  private final Lock lock = new ReentrantLock();

  // 创建3个condition
  private final Condition c1 = lock.newCondition();
  private final Condition c2 = lock.newCondition();
  private final Condition c3 = lock.newCondition();

  /**
   * 打印5次，一共打印round轮
   *
   * @param round 第几轮
   */
  public void print5(int round) throws InterruptedException {
    // 上锁
    lock.lock();
    try {
      // 判断
      while (flag != 1) {
        // 等待
        c1.await();
      }
      for (int i = 0; i < 5; i++) {
        System.out.println(Thread.currentThread().getName() + ", i=" + i + ", 第" + round + "轮");
      }
      // 通知
      flag = 2; // 修改标志位为2
      c2.signal(); // 通知线程B
    } finally {
      // 释放锁
      lock.unlock();
    }
  }

  /**
   * 打印10次，一共打印round轮
   *
   * @param round 第几轮
   */
  public void print10(int round) throws InterruptedException {
    // 上锁
    lock.lock();
    try {
      // 判断
      while (flag != 2) {
        // 等待
        c2.await();
      }
      for (int i = 0; i < 10; i++) {
        System.out.println(Thread.currentThread().getName() + ", i=" + i + ", 第" + round + "轮");
      }
      // 通知
      flag = 3; // 修改标志位为3
      c3.signal(); // 通知线程C
    } finally {
      // 释放锁
      lock.unlock();
    }
  }

  /**
   * 打印15次，一共打印round轮
   *
   * @param round 第几轮
   */
  public void print15(int round) throws InterruptedException {
    // 上锁
    lock.lock();
    try {
      // 判断
      while (flag != 3) {
        // 等待
        c3.await();
      }
      for (int i = 0; i < 5; i++) {
        System.out.println(Thread.currentThread().getName() + ", i=" + i + ", 第" + round + "轮");
      }
      // 通知
      flag = 1; // 修改标志位为1
      c1.signal(); // 通知线程A
    } finally {
      // 释放锁
      lock.unlock();
    }
  }
}

