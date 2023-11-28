package com.laioffer.learn.ch3_线程间通信.p1_wait_notify机制.demo4_sleep不释放锁;

/**
 * 类描述: sleep不释放锁
 * <p>
 * created by 春夏秋冬在中南 on 2023/11/28 22:52
 */
public class SleepNotReleaseLock {
  public static void main(String[] args) {
    Object lock = new Object();

    ThreadA a = new ThreadA(lock);
    a.start();

    ThreadB b = new ThreadB(lock);
    b.start();
  }
}

class ThreadA extends Thread {
  private final Object mLock;

  public ThreadA(Object lock) {
    mLock = lock;
  }

  @Override
  public void run() {
    Service service = new Service();
    service.testMethod(mLock);
  }
}

class ThreadB extends Thread {
  private final Object mLock;

  public ThreadB(Object lock) {
    mLock = lock;
  }

  @Override
  public void run() {
    Service service = new Service();
    service.testMethod(mLock);
  }
}

class Service {
  public void testMethod(Object lock) {
    try {
      synchronized (lock) {
        System.out.println("begin wait()");
        Thread.sleep(4000);
        System.out.println("\t end wait()");
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

/*
打印结果：
    begin wait()
       end wait()
    begin wait()
       end wait()
 */