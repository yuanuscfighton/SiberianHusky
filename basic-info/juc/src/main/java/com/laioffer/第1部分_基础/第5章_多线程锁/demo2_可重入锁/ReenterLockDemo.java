package com.laioffer.第1部分_基础.第5章_多线程锁.demo2_可重入锁;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类的描述: Lock 可重入锁 or 递归锁
 * Created by 春夏秋冬在中南 on 2023/10/7 23:18
 */
public class ReenterLockDemo {

  public static void main(String[] args) {
    Lock lock = new ReentrantLock();

    new Thread(() -> {
      try {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "\t 外层锁🔐");

        try {
          lock.lock();
          System.out.println(Thread.currentThread().getName() + "\t 内层锁🔐");
        } finally {
          lock.unlock();
        }
      } finally {
        lock.unlock();
      }
    }, "线程2").start();
  }
}

/*
  打印结果
      线程2	 外层锁🔐
      线程2	 内层锁🔐
 */