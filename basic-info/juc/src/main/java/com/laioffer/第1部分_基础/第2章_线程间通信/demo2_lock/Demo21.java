package com.laioffer.第1部分_基础.第2章_线程间通信.demo2_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类的描述: 使用Lock实现线程之间通信
 * Created by 春夏秋冬在中南 on 2023/9/17 21:25
 */
public class Demo21 {

  public static void main(String[] args) {
    Share21 share = new Share21();
    new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        try {
          share.increase();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }, "AA").start();

    new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        try {
          share.decrease();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }, "BB").start();

    new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        try {
          share.increase();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }, "CC").start();

    new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        try {
          share.decrease();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }, "DD").start();
  }
}

class Share21 {
  private int number = 0;

  private final Lock mLock = new ReentrantLock();
  private final Condition mCondition = mLock.newCondition();

  public void increase() throws InterruptedException {
    // 上锁
    mLock.lock();

    try {
      while (number != 0) {
        mCondition.await();
      }

      // 干活
      number++;
      System.out.println(Thread.currentThread().getName() + "::" + number);

      // 通知
      mCondition.signalAll();
    } finally {
      // 解锁
      mLock.unlock();
    }
  }

  public void decrease() throws InterruptedException {
    // 上锁
    mLock.lock();

    try {
      while (number != 1) {
        mCondition.await();
      }

      number--;
      System.out.println(Thread.currentThread().getName() + "::" + number);

      // 通知
      mCondition.signalAll();
    } finally {
      // 解锁
      mLock.unlock();
    }
  }
}
