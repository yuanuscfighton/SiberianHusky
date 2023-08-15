package com.laioffer.第2部分_高级.第4章_线程中断.demo5_locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 类的描述: LockSupport中的park和unpark
 * Created by 春夏秋冬在中南 on 2023/7/25 08:29
 */
public class Demo451 {

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

/*
  1.调用park()方法
    permit许可证默认没有，不能放行，所以一开始调用park方法，当前线程就会阻塞，直到别的线程给当前线程发放permit，park方法才会被唤醒

  2.调用unpark(thread)方法
    调用unpark方法后，就会将thread线程的许可证permit发放，互自动唤醒park线程，即之前阻塞中的LockSupport.park()方法会立即返回

 */
