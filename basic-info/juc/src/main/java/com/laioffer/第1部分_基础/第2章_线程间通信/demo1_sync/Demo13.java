package com.laioffer.第1部分_基础.第2章_线程间通信.demo1_sync;

/**
 * 类的描述: 线程间通信
 * Created by 春夏秋冬在中南 on 2023/9/17 16:46
 */
public class Demo13 {
  public static void main(String[] args) {
    Share13 share = new Share13();

    // 第3步：创建多个线程，调用资源类的操作方法
    new Thread(() -> {
      for (int i = 0; i < 100; i++) {
        try {
          share.increase();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }, "线程1").start();

    new Thread(() -> {
      for (int i = 0; i < 100; i++) {
        try {
          share.decrease();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }, "线程2").start();

    new Thread(() -> {
      for (int i = 0; i < 100; i++) {
        try {
          share.increase();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }, "线程3").start();

    new Thread(() -> {
      for (int i = 0; i < 100; i++) {
        try {
          share.decrease();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }, "线程4").start();
  }
}

// 第1步：创建资源类，在资源类中创建属性和操作方法
class Share13 {
  private int number = 0;

  public synchronized void increase() throws InterruptedException {
    // 第2步：在资源类操作方法中，判断 → 干活 → 通知
    /* if (number != 0) { // 解决方案 → if 改成 while */
    while (number != 0) {
      // 等待
      this.wait(); // 使用while后，都会重新走一遍while
    }
    number++;
    System.out.println(Thread.currentThread().getName() + "::" + number);
    // 通知其它线程
    this.notifyAll();
  }

  public synchronized void decrease() throws InterruptedException {
    while (number != 1) {
      this.wait();
    }
    number--;
    System.out.println(Thread.currentThread().getName() + "::" + number);
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