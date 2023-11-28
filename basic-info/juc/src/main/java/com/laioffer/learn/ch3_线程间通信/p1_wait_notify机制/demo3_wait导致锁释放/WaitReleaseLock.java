package com.laioffer.learn.ch3_线程间通信.p1_wait_notify机制.demo3_wait导致锁释放;

/**
 * 类描述: 方法wait被执行后，锁会被立即释放
 * <p>
 * created by 春夏秋冬在中南 on 2023/11/28 22:45
 */
public class WaitReleaseLock {
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
        lock.wait();
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
    begin wait()

结论：
    方法wait()被执行后，锁会被立即释放，但执行完notify()方法后，锁不会立即释放
 */