package com.laioffer.learn.ch2_对象及变量的并发访问.p3_volatile关键字.demo2_原子性;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 类描述: 使用同步解决方法与方法之间调用是非原子的问题
 * <p>
 * created by 春夏秋冬在中南 on 2023/11/26 17:37
 */
public class Demo238 {
  public static void main(String[] args) {
    try {
      MyService1 service = new MyService1();
      MyThread1[] array = new MyThread1[5];

      for (int i = 0; i < array.length; i++) {
        array[i] = new MyThread1(service);
        array[i].setName("线程" + i);
      }

      for (int i = 0; i < array.length; i++) {
        array[i].start();
      }

      Thread.sleep(1000);

      System.out.println(service.aiRef.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

class MyThread1 extends Thread {
  private final MyService1 mMyService;

  public MyThread1(MyService1 mMyService) {
    super();
    this.mMyService = mMyService;
  }

  @Override
  public void run() {
    mMyService.addNum();
  }
}

class MyService1 {

  public static AtomicLong aiRef = new AtomicLong();

  synchronized public void addNum() {
    System.out.println(Thread.currentThread().getName() + "\t 加了100之后的值是:" + aiRef.addAndGet(100));
    aiRef.addAndGet(1);
  }
}

/*
打印结果：
    线程0   加了100之后的值是:100
    线程3   加了100之后的值是:201
    线程4   加了100之后的值是:302
    线程2   加了100之后的值是:403
    线程1   加了100之后的值是:504
    505
 */