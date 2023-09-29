package com.laioffer.第1部分_基础.第2章_线程间通信.demo1_sync;

/**
 * 类的描述: 线程间通信
 * Created by 春夏秋冬在中南 on 2023/9/17 16:46
 */
public class Demo11 {
  public static void main(String[] args) {
    Share11 share = new Share11();

    // 第3步：创建多个线程，调用资源类的操作方法
    new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        try {
          share.increase();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }, "线程1").start();

    new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        try {
          share.decrease();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }, "线程2").start();
  }
}

// 第1步：创建资源类，在资源类中创建属性和操作方法
class Share11 {
  private int number = 0;

  public synchronized void increase() throws InterruptedException {
    // 第2步：在资源类操作方法中，判断 → 干活 → 通知
    if (number != 0) {
      // 只有number是0的时候，才+1，否则等待
      this.wait(); /* wait会释放锁 → 其它线程就可以抢到锁🔐了 */
    }

    number++;
    System.out.println(Thread.currentThread().getName() + "::" + number);
    // 通知其它线程
    this.notifyAll();
  }

  public synchronized void decrease() throws InterruptedException {
    // 第2步：在资源类操作方法中，判断 → 干活 → 通知
    if (number != 1) {
      // 只有number是1的时候，才-1，否则等待
      this.wait();
    }

    number--;
    System.out.println(Thread.currentThread().getName() + "::" + number);
    // 通知其它线程
    this.notifyAll();
  }
}

/*
打印结果：
  线程1::1
  线程2::0
  线程1::1
  线程2::0
  线程1::1
  线程2::0
  线程1::1
  线程2::0
  线程1::1
  线程2::0
 */