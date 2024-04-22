package com.laioffer.第1部分_基础.第3章_线程间定制化通信;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类的描述: 线程间定制化通信
 * <p>
 * demo2_lock中的线程执行顺序不一定，可能是 AA DD ... 也可能是 AA CC
 * <p>
 * 需求：启动三个线程，按照如下要求，让AA线程打印2次，让BB打印3次，最后让CC打印5次，以此执行10轮
 * 方案：设置一个标志位 flag，AA-flag=1，BB-flag=2，CC-flag=3
 * <p>
 * Created by 春夏秋冬在中南 on 2023/9/17 21:47
 */
public class Demo31 {

  public static void main(String[] args) {
    ShareResource shareResource = new ShareResource();
    new Thread(() -> {
      for (int i = 0; i < 3; i++) {
        try {
          shareResource.print2(i);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }, "AA").start();

    new Thread(() -> {
      for (int i = 0; i < 3; i++) {
        try {
          shareResource.print3(i);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }, "BB").start();

    new Thread(() -> {
      for (int i = 0; i < 3; i++) {
        try {
          shareResource.print5(i);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }, "CC").start();
  }
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

  /**
   * 打印2次
   *
   * @param loop 轮次信息
   */
  public void print2(int loop) throws InterruptedException {
    // 上锁
    lock.lock();
    try {
      // 判断
      while (flag != 1) {
        // 等待
        c1.await();
      }
      // 干活
      for (int i = 0; i < 2; i++) {
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

  /**
   * 打印3次
   */
  public void print3(int loop) throws InterruptedException {
    // 上锁
    lock.lock();
    try {
      // 判断
      while (flag != 2) {
        // 等待
        c2.await();
      }
      for (int i = 0; i < 3; i++) {
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

  /**
   * 打印5次
   */
  public void print5(int loop) throws InterruptedException {
    // 上锁
    lock.lock();
    try {
      // 判断
      while (flag != 3) {
        // 等待
        c3.await();
      }
      for (int i = 0; i < 5; i++) {
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

/*
    AA::0... 轮次=0
    AA::1... 轮次=0
    BB::0... 轮次=0
    BB::1... 轮次=0
    BB::2... 轮次=0
    CC::0... 轮次=0
    CC::1... 轮次=0
    CC::2... 轮次=0
    CC::3... 轮次=0
    CC::4... 轮次=0
    AA::0... 轮次=1
    AA::1... 轮次=1
    BB::0... 轮次=1
    BB::1... 轮次=1
    BB::2... 轮次=1
    CC::0... 轮次=1
    CC::1... 轮次=1
    CC::2... 轮次=1
    CC::3... 轮次=1
    CC::4... 轮次=1
    AA::0... 轮次=2
    AA::1... 轮次=2
    BB::0... 轮次=2
    BB::1... 轮次=2
    BB::2... 轮次=2
    CC::0... 轮次=2
    CC::1... 轮次=2
    CC::2... 轮次=2
    CC::3... 轮次=2
    CC::4... 轮次=2
 */