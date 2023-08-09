package com.laioffer.第2部分_高级.第4章_线程中断.demo3_await_signal;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类的描述: Lock中的await和signal方法，先signal在await
 * Created by 春夏秋冬在中南 on 2023/7/25 07:19
 */
public class Demo533 {

  public static void main(String[] args) {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();


    new Thread(() -> {
      lock.lock();
      try {
        condition.signal();
        System.out.println(Thread.currentThread().getName() + "\t 发出通知");
      } finally {
        lock.unlock();
      }
    }, "[线程531 线程2]").start();

    // 暂停几毫秒线程
    try {
      TimeUnit.MILLISECONDS.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    new Thread(() -> {
      lock.lock();
      try {
        System.out.println(Thread.currentThread().getName() + "\t [1]");
        condition.await();
        System.out.println(Thread.currentThread().getName() + "\t 被唤醒");
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        lock.unlock();
      }
    }, "[线程531 线程1]").start();

  }
}

/*
线程无法被唤醒

[线程531 线程2]	 发出通知
[线程531 线程1]	 [1]
 */
