package com.laioffer.learn.ch2_对象及变量的并发访问.p3_volatile关键字.demo2_原子性;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 类描述: 原子类的输出结果也具有随机性
 * <p>
 * created by 春夏秋冬在中南 on 2023/11/26 17:25
 */
public class Demo237 {
  public static void main(String[] args) {
    try {
      MyService service = new MyService();
      MyThread[] array = new MyThread[5];

      for (int i = 0; i < array.length; i++) {
        array[i] = new MyThread(service);
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

class MyThread extends Thread {
  private final MyService mMyService;

  public MyThread(MyService mMyService) {
    super();
    this.mMyService = mMyService;
  }

  @Override
  public void run() {
    mMyService.addNum();
  }
}

class MyService {
  public static AtomicLong aiRef = new AtomicLong();

  public void addNum() {
    System.out.println(Thread.currentThread().getName() + "\t 加了100之后的值是:" + aiRef.addAndGet(100));
    aiRef.addAndGet(1);
  }
}

/*
打印结果：
    线程0	 加了100之后的值是:100
    线程3	 加了100之后的值是:400
    线程2	 加了100之后的值是:300
    线程1	 加了100之后的值是:200
    线程4	 加了100之后的值是:501
    505

现象：输出顺序出错了，应该每加1次100再加1次1

原因：出现这种情况的原因是addAndGet()方法是原子的，但方法和方法之间的调用却是非原子的，此时可以用同步解决该问题。
 */