package com.laioffer.learn.ch2_对象及变量的并发访问.p3_volatile关键字.Demo1_可见性;

/**
 * 类描述: synchronized代码块也具有增加可见性作用
 * <p>
 * created by 春夏秋冬在中南 on 2023/11/26 16:40
 */
public class Demo234 {
  public static void main(String[] args) {
    try {
      Service service = new Service();

      ThreadA a = new ThreadA(service);
      a.start();

      Thread.sleep(1000);

      ThreadB b = new ThreadB(service);
      b.start();

      System.out.println("已经发起停止的命令了!");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}


class ThreadA extends Thread {
  private final Service service;

  public ThreadA(Service service) {
    super();
    this.service = service;
  }

  @Override
  public void run() {
    service.runMethod();
  }
}

class ThreadB extends Thread {
  private final Service service;

  public ThreadB(Service service) {
    super();
    this.service = service;
  }

  @Override
  public void run() {
    service.stopMethod();
  }
}

class Service {
  private boolean isContinueRun = true;

  public void runMethod() {
    while (isContinueRun) {
    }
    System.out.println("停下来了!");
  }

  public void stopMethod() {
    isContinueRun = false;
  }
}
