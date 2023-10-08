package com.laioffer.第1部分_基础.第5章_多线程锁.demo1_公平锁_非公平锁;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 类的描述: 公平锁
 * Created by 春夏秋冬在中南 on 2023/10/7 22:30
 */
public class FairLock {

  public static void main(String[] args) {

    FairSaleTickets mLTicket = new FairSaleTickets();

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

class FairSaleTickets {
  private int number = 100;
  // 公平锁
  private final ReentrantLock mLock = new ReentrantLock(true);

  public void sale() {
    mLock.lock();
    try {
      if (number > 0) {
        System.out.println(Thread.currentThread().getName() + "\t卖出: " + (number--) + " \t剩余: " + number);
      }
    } finally {
      mLock.unlock();
    }
  }
}