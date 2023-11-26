package com.laioffer.learn.ch3_线程间通信.p1_wait_notify机制.demo2_wait_notify通信;

/**
 * 类描述: 使用代码完整实现wait/notify机制
 * <p>
 * created by 春夏秋冬在中南 on 2023/11/26 21:15
 */
public class Demo21 {
  public static void main(String[] args) {
    try {
      Object lock = new Object();
      MyThread1 t1 = new MyThread1(lock);
      t1.start();

      Thread.sleep(3000);

      MyThread2 t2 = new MyThread2(lock);
      t2.start();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

class MyThread1 extends Thread {
  private final Object lock;

  public MyThread1(Object lock) {
    super();
    this.lock = lock;
  }

  @Override
  public void run() {
    try {
      synchronized (lock) {
        System.out.println("开始\t wait time=" + System.currentTimeMillis());
        lock.wait();
        System.out.println("结束\t wait time=" + System.currentTimeMillis());
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

class MyThread2 extends Thread {
  private final Object lock;

  public MyThread2(Object lock) {
    super();
    this.lock = lock;
  }

  @Override
  public void run() {
    synchronized (lock) {
      System.out.println("开始\t notify time=" + System.currentTimeMillis());
      lock.notify();
      System.out.println("结束\t notify time=" + System.currentTimeMillis());
    }
  }
}

/*
打印结果：
    开始	 wait time=1701004774276
    开始	 notify time=1701004777279
    结束	 notify time=1701004777279
    结束	 wait time=1701004777279
 */