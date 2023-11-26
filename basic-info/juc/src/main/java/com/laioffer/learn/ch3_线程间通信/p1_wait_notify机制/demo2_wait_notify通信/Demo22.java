package com.laioffer.learn.ch3_线程间通信.p1_wait_notify机制.demo2_wait_notify通信;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述: 使用wait/notify机制实现线程销毁
 * <p>
 * created by 春夏秋冬在中南 on 2023/11/26 21:20
 */
public class Demo22 {
  public static void main(String[] args) {

    try {
      Object lock = new Object();

      ThreadA a = new ThreadA(lock);
      a.start();

      Thread.sleep(50);

      ThreadB b = new ThreadB(lock);
      b.start();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}


class ThreadA extends Thread {

  private final Object lock;

  public ThreadA(Object lock) {
    super();
    this.lock = lock;
  }

  @Override
  public void run() {
    try {
      synchronized (lock) {
        if (MyList.size() != 5) {
          System.out.println("wait begin " + System.currentTimeMillis());
          lock.wait();
          System.out.println("wait end  " + System.currentTimeMillis());
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

class ThreadB extends Thread {
  private final Object lock;

  public ThreadB(Object lock) {
    super();
    this.lock = lock;
  }

  @Override
  public void run() {
    try {
      synchronized (lock) {
        for (int i = 0; i < 10; i++) {
          MyList.add();

          if (MyList.size() == 5) {
            lock.notify();
            System.out.println("已发出通知!");
          }

          System.out.println("添加了" + (i + 1) + "个元素!");
          Thread.sleep(1000);
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

class MyList {
  private static final List<String> list = new ArrayList<>();

  public static void add() {
    list.add("anyString");
  }

  public static int size() {
    return list.size();
  }
}

/*
打印结果：
    wait begin 1701005194921
    添加了1个元素!
    添加了2个元素!
    添加了3个元素!
    添加了4个元素!
    已发出通知!
    添加了5个元素!
    添加了6个元素!
    添加了7个元素!
    添加了8个元素!
    添加了9个元素!
    添加了10个元素!
    wait end  1701005205000
 */
