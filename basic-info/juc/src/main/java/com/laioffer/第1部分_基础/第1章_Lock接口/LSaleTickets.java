package com.laioffer.第1部分_基础.第1章_Lock接口;

import java.util.concurrent.locks.Lock;
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
  private int number = 30;

  private final Lock mLock = new ReentrantLock();

  public void sale() {
    // 手动上锁
    mLock.lock();
    try {
      if (number > 0) {
        System.out.println(Thread.currentThread().getName() + "当前已经卖出: " + (number--) + " 张票, 此时还剩余: " + number + " 张票");
      }
    } finally {
      // 手动解锁/释放锁
      mLock.unlock();
    }
  }
}

/*
    线程a当前已经卖出: 30 张票, 此时还剩余: 29 张票
    线程a当前已经卖出: 29 张票, 此时还剩余: 28 张票
    线程a当前已经卖出: 28 张票, 此时还剩余: 27 张票
    线程a当前已经卖出: 27 张票, 此时还剩余: 26 张票
    线程a当前已经卖出: 26 张票, 此时还剩余: 25 张票
    线程a当前已经卖出: 25 张票, 此时还剩余: 24 张票
    线程a当前已经卖出: 24 张票, 此时还剩余: 23 张票
    线程a当前已经卖出: 23 张票, 此时还剩余: 22 张票
    线程a当前已经卖出: 22 张票, 此时还剩余: 21 张票
    线程a当前已经卖出: 21 张票, 此时还剩余: 20 张票
    线程a当前已经卖出: 20 张票, 此时还剩余: 19 张票
    线程a当前已经卖出: 19 张票, 此时还剩余: 18 张票
    线程a当前已经卖出: 18 张票, 此时还剩余: 17 张票
    线程a当前已经卖出: 17 张票, 此时还剩余: 16 张票
    线程a当前已经卖出: 16 张票, 此时还剩余: 15 张票
    线程a当前已经卖出: 15 张票, 此时还剩余: 14 张票
    线程a当前已经卖出: 14 张票, 此时还剩余: 13 张票
    线程a当前已经卖出: 13 张票, 此时还剩余: 12 张票
    线程a当前已经卖出: 12 张票, 此时还剩余: 11 张票
    线程a当前已经卖出: 11 张票, 此时还剩余: 10 张票
    线程a当前已经卖出: 10 张票, 此时还剩余: 9 张票
    线程a当前已经卖出: 9 张票, 此时还剩余: 8 张票
    线程a当前已经卖出: 8 张票, 此时还剩余: 7 张票
    线程a当前已经卖出: 7 张票, 此时还剩余: 6 张票
    线程b当前已经卖出: 6 张票, 此时还剩余: 5 张票
    线程b当前已经卖出: 5 张票, 此时还剩余: 4 张票
    线程b当前已经卖出: 4 张票, 此时还剩余: 3 张票
    线程b当前已经卖出: 3 张票, 此时还剩余: 2 张票
    线程b当前已经卖出: 2 张票, 此时还剩余: 1 张票
    线程b当前已经卖出: 1 张票, 此时还剩余: 0 张票
 */
