package com.laioffer.learn.ch3_线程间通信.p1_wait_notify机制.demo5_notify不立即释放锁;

/**
 * 类描述: 方法notify被执行后，不立即释放锁
 * <p>
 * created by 春夏秋冬在中南 on 2023/11/28 22:56
 */
public class NotifyHoldLock {
  public static void main(String[] args) throws InterruptedException {
    MyService myService = new MyService();

    MyThreadA a = new MyThreadA(myService);
    a.setName("线程a");
    a.start();

    Thread.sleep(50);

    MyThreadB b = new MyThreadB(myService);
    b.setName("线程b");
    b.start();
  }
}


class MyThreadA extends Thread {
  private final MyService myService;

  public MyThreadA(MyService myService) {
    super();
    this.myService = myService;
  }

  @Override
  public void run() {
    myService.waitMethod();
  }
}

class MyThreadB extends Thread {
  private final MyService myService;

  public MyThreadB(MyService myService) {
    super();
    this.myService = myService;
  }

  @Override
  public void run() {
    myService.notifyMethod();
  }
}

class MyService {
  private final Object lock = new Object();

  public void waitMethod() {
    try {
      synchronized (lock) {
        System.out.println("begin wait() 线程名=" + Thread.currentThread().getName()
            + " time=" + System.currentTimeMillis());
        lock.wait();
        System.out.println("\t end wait() 线程名=" + Thread.currentThread().getName()
            + " time=" + System.currentTimeMillis());
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void notifyMethod() {
    try {
      synchronized (lock) {
        System.out.println("begin notify() 线程名=" + Thread.currentThread().getName()
            + " time=" + System.currentTimeMillis());
        lock.notify();
        Thread.sleep(5000);
        System.out.println("\t end notify() 线程名=" + Thread.currentThread().getName()
            + " time=" + System.currentTimeMillis());
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

/*
打印结果：
    begin wait() 线程名=线程a time=1701183756806
    begin notify() 线程名=线程b time=1701183756860
       end notify() 线程名=线程b time=1701183761860
       end wait() 线程名=线程a time=1701183761860

 分析：
    必须执行完notify()方法所在的同步代码块之后才释放锁
 */