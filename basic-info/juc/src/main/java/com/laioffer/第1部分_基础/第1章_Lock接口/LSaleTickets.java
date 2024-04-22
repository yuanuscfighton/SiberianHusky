package com.laioffer.第1部分_基础.第1章_Lock接口;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 类的描述: 使用Lock接口进行卖票
 * <p>
 * Created by 春夏秋冬在中南 on 2023/9/14 07:47
 */
public class LSaleTickets {
  public static void main(String[] args) {

    LTicket mLTicket = new LTicket();

    // 第2步：创建多个线程，调用资源类中的操作方法
    // 创建三个线程
    new Thread(() -> {
      for (int i = 0; i < 30; i++) {
        mLTicket.sale();
      }
    }, "线程a").start();

    new Thread(() -> {
      for (int i = 0; i < 30; i++) {
        mLTicket.sale();
      }
    }, "线程b").start();

    new Thread(() -> {
      for (int i = 0; i < 30; i++) {
        mLTicket.sale();
      }
    }, "线程c").start();
  }
}

/**
 * 第1步：创建资源类，定义属性和操作方法
 */
class LTicket {
  private int number = 100;

  private final ReentrantLock mLock = new ReentrantLock();

  public void sale() {
    // 上锁
    mLock.lock();
    try {
      if (number > 0) {
        System.out.println(Thread.currentThread().getName() + "当前已经卖出: " + (number--) + " 张票, 此时还剩余: " + number + " 张票");
      }
    } finally {
      // 解锁 / 释放锁
      mLock.unlock();
    }
  }
}
