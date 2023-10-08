package com.laioffer.第1部分_基础.第5章_多线程锁.demo2_可重入锁;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类的描述: 有问题：Lock 可重入锁 or 递归锁
 * Created by 春夏秋冬在中南 on 2023/10/7 23:18
 */
public class ReenterLockDemo1 {

  public static void main(String[] args) {
    Lock lock = new ReentrantLock();

    new Thread(() -> {
      lock.lock();
      System.out.println(Thread.currentThread().getName() + "\t 外层锁🔐");

      lock.lock();

      System.out.println(Thread.currentThread().getName() + "\t 内层锁🔐");

      lock.unlock();
    }, "线程3a").start();

    new Thread(() -> {
      lock.lock();
      System.out.println(Thread.currentThread().getName() + "\t\t aaaa");
      lock.unlock();
    }, "线程3b").start();
  }
}

/*
  打印结果
      线程3a	 外层锁🔐
      线程3a	 内层锁🔐
      ...
      (卡住了 ... ...)
 */