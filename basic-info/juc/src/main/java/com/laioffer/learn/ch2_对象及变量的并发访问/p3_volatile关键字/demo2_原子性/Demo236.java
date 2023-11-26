package com.laioffer.learn.ch2_对象及变量的并发访问.p3_volatile关键字.demo2_原子性;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类描述: 使用Atomic原子类进行i++操作实现原子性
 * <p>
 * created by 春夏秋冬在中南 on 2023/11/26 17:23
 */
public class Demo236 {
  public static void main(String[] args) {
    AddCountThread countService = new AddCountThread();

    Thread t1 = new Thread(countService);
    t1.start();

    Thread t2 = new Thread(countService);
    t2.start();

    Thread t3 = new Thread(countService);
    t3.start();

    Thread t4 = new Thread(countService);
    t4.start();

    Thread t5 = new Thread(countService);
    t5.start();
  }
}

class AddCountThread extends Thread {
  private final AtomicInteger count = new AtomicInteger(0);

  @Override
  public void run() {
    for (int i = 0; i < 10000; i++) {
      System.out.println(count.incrementAndGet());
    }
  }
}
