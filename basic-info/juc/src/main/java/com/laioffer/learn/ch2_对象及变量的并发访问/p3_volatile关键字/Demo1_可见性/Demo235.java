package com.laioffer.learn.ch2_对象及变量的并发访问.p3_volatile关键字.Demo1_可见性;

/**
 * 类描述: synchronized代码块也具有增加可见性作用
 * <p>
 * created by 春夏秋冬在中南 on 2023/11/26 16:40
 */
public class Demo235 {
  public static void main(String[] args) {
    try {
      Service1 service = new Service1();

      ThreadA1 a = new ThreadA1(service);
      a.start();

      Thread.sleep(1000);

      ThreadB1 b = new ThreadB1(service);
      b.start();

      System.out.println("已经发起停止的命令了!");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}


class ThreadA1 extends Thread {
  private final Service1 service;

  public ThreadA1(Service1 service) {
    super();
    this.service = service;
  }

  @Override
  public void run() {
    service.runMethod();
  }
}

class ThreadB1 extends Thread {
  private final Service1 service;

  public ThreadB1(Service1 service) {
    super();
    this.service = service;
  }

  @Override
  public void run() {
    service.stopMethod();
  }
}

class Service1 {
  private boolean isContinueRun = true;

  public void runMethod() {
    String anyString = "";
    while (isContinueRun) {
      synchronized (anyString) {
      }
    }
    System.out.println("停下来了!");
  }

  public void stopMethod() {
    isContinueRun = false;
  }
}
