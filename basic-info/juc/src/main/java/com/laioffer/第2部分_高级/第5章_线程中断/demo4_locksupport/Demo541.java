package com.laioffer.第2部分_高级.第5章_线程中断.demo4_locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 类的描述: LockSupport中的park和unpark
 * Created by 春夏秋冬在中南 on 2023/7/25 08:29
 */
public class Demo541 {

  public static void main(String[] args) {
    Thread t1 = new Thread(() -> {
      System.out.println(Thread.currentThread().getName() + "\t [1]");
      LockSupport.park();
      System.out.println(Thread.currentThread().getName() + "\t 被唤醒了");
    }, "demo541 线程1");
    t1.start();

    // 暂停几毫秒线程
    try {
      TimeUnit.MILLISECONDS.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    Thread t2 = new Thread(() -> {
      LockSupport.unpark(t1);
      System.out.println(Thread.currentThread().getName() + "\t 发出通知");
    }, "demo541 线程2");
    t2.start();
  }
}
