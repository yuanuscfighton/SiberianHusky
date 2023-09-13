package com.laioffer.第1部分_基础.第1章_Lock接口;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 类的描述: 使用Lock接口进行卖票
 * Created by 春夏秋冬在中南 on 2023/9/14 07:47
 */
public class LSalTickets {
  public static void main(String[] args) {

    LTicket mLTicket = new LTicket();

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

class LTicket {
  private int number = 100;
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
