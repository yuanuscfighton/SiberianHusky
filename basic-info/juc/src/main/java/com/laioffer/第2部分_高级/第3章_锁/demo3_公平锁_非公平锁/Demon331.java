package com.laioffer.第2部分_高级.第3章_锁.demo3_公平锁_非公平锁;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 类的描述: 模拟3个售票员卖完50张票
 * Created by 春夏秋冬在中南 on 2023/7/15 17:43
 */
public class Demon331 {
  public static void main(String[] args) {
    Ticket ticket = new Ticket();
    new Thread(
        () -> {
          for (int i = 0; i < 55; i++) {
            ticket.sale();
          }
        },
        "aaa"
    )
        .start();

    new Thread(
        () -> {
          for (int i = 0; i < 55; i++) {
            ticket.sale();
          }
        },
        "bbb"
    )
        .start();

    new Thread(
        () -> {
          for (int i = 0; i < 55; i++) {
            ticket.sale();
          }
        },
        "ccc"
    )
        .start();
  }
}

class Ticket {
  private int number = 50;
  ReentrantLock lock = new ReentrantLock(true);

  public void sale() {
    lock.lock();

    try {
      if (number > 0) {
        System.out.println(Thread.currentThread().getName() + "卖出第[" + (number--) + "]张票，还剩[" + number + "]张");
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }
}

/*
什么是"公平锁"、"非公平锁"？

1.公平锁：是指多个线程按照申请锁的顺序来获取锁的，类似排队买票，先来的人先买，后来的人在队尾排着，这个就是公平锁。
Lock lock = new ReentrantLock(true); // true表示公平锁，先来先得

2.非公平锁：是指多个线程获取锁的顺序不是按照申请锁的顺序，有可能后面申请的线程 必先申请的线程优先获取锁，在高并发环境下，
    有可能造成优先级翻转或者饥饿的状态(i.e. 某个线程一直得不到锁)
    Lock lock = new ReentrantLock(false); // false非公平锁，后来的也可能先获取锁
    Lock lock = new ReentrantLock(); // 默认是非公平锁
 */


