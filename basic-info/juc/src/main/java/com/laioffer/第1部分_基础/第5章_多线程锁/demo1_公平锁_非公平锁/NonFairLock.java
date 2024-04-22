package com.laioffer.第1部分_基础.第5章_多线程锁.demo1_公平锁_非公平锁;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 类的描述: 非公平锁
 * <p>
 * Created by 春夏秋冬在中南 on 2023/10/7 22:31
 */
public class NonFairLock {

  public static void main(String[] args) {

    NonFairSaleTickets mLTicket = new NonFairSaleTickets();

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

class NonFairSaleTickets {
  private int number = 100;

  // 非公平锁，会导致线程饿死，效率高
  private final ReentrantLock mLock = new ReentrantLock();

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
