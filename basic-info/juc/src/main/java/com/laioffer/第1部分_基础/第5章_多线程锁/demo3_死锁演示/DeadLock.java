package com.laioffer.第1部分_基础.第5章_多线程锁.demo3_死锁演示;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: 死锁案例
 * <p>
 * Created by 春夏秋冬在中南 on 2023/10/8 22:42
 */
public class DeadLock {

  private static final Object o1 = new Object();
  private static final Object o2 = new Object();

  public static void main(String[] args) {
    new Thread(() -> {
      synchronized (o1) {
        System.out.println(Thread.currentThread().getName() + "持有锁o1，试图获取锁o2");

        // 暂停几毫秒线程 → 在线程2获取锁之前，线程1就执行完了，所以为了大概率出现死锁现象，这里sleep 1秒
        try {
          TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        synchronized (o2) {
          System.out.println(Thread.currentThread().getName() + "获取到锁o2");
        }
      }
    }, "线程1").start();

    new Thread(() -> {
      synchronized (o2) {
        System.out.println(Thread.currentThread().getName() + "持有锁o2，试图获取锁o1");

        // 暂停几毫秒线程
        try {
          TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        synchronized (o1) {
          System.out.println(Thread.currentThread().getName() + "获取到锁o1");
        }
      }
    }, "线程2").start();
  }
}

/*
  打印：
    线程1持有锁o1，试图获取锁o2
    线程2持有锁o2，试图获取锁o1
    ... 程序没有停止⏹
 */