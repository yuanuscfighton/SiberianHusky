package com.laioffer.第2部分_高级.第4章_线程中断.demo3_wait_notify;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: Object中的wait和notify
 * Created by 春夏秋冬在中南 on 2023/7/25 07:00
 */
public class Demo431 {

  public static void main(String[] args) {
    Object objectLock = new Object();

    // 线程1 wait
    new Thread(() -> {
      synchronized (objectLock) {
        System.out.println(Thread.currentThread().getName() + "\t [1]");
        try {
          objectLock.wait(); // 👈🏻 一旦调用wait()方法后，就会交出锁🔐的控制权，其它线程就会争抢这把锁，但我就在这里阻塞
        } catch (Exception e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t 被唤醒");
      }
    }, "线程1").start();

    // 暂停几毫秒线程
    try {
      TimeUnit.MILLISECONDS.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // 线程2 notify
    new Thread(() -> {
      synchronized (objectLock) {
        objectLock.notify();
        System.out.println(Thread.currentThread().getName() + "\t 通知");
      }
    }, "线程2").start();
  }
}


/*
  线程1	 [1]
  线程2	 通知
  线程1	 被唤醒

  进入线程1走到wait方法，停滞在这，过了1秒后，线程2启动了，立即发送notify通知，
  线程1和线程2持有同一把锁，谁抢到，谁用，另一个继续等待锁的释放

 */