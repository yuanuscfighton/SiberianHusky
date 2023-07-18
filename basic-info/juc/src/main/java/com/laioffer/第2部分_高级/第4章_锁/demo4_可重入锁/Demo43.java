package com.laioffer.第2部分_高级.第4章_锁.demo4_可重入锁;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 类的描述: 可重入锁: ReentrantLock
 * Created by 春夏秋冬在中南 on 2023/7/18 23:19
 */
public class Demo43 {

  private static ReentrantLock lock = new ReentrantLock();

  public static void main(String[] args) {
    new Thread(() -> {
      lock.lock();
      try {
        System.out.println(Thread.currentThread().getName() + "\t 外层调用");
        lock.lock();
        try {
          System.out.println(Thread.currentThread().getName() + "\t 内层调用");
        } finally {
          // lock.unlock(); // 由于加锁次数和释放次数不一样，第2个线程始终无法获取到锁，导致一直在等待
        }
      } finally {
        lock.unlock();
      }
    }, "线程a").start();

    new Thread(() -> {
      lock.lock();
      try {
        System.out.println(Thread.currentThread().getName() + "\t 外层调用");
      } finally {
        lock.unlock();
      }
    }, "线程b").start();
  }
}

/*
正常情况下，加锁几次，就需要解锁几次
 */