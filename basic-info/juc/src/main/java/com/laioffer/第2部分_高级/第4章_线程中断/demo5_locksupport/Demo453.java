package com.laioffer.第2部分_高级.第4章_线程中断.demo5_locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 类的描述:LockSupport中的park和unpark，验证是否支持多个许可证
 * Created by 春夏秋冬在中南 on 2023/7/25 08:34
 */
public class Demo453 {

  public static void main(String[] args) {
    Thread t1 = new Thread(() -> {
      // 暂停几毫秒线程
      try {
        TimeUnit.MILLISECONDS.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      System.out.println(Thread.currentThread().getName() + "\t 进入 " + System.currentTimeMillis());
      LockSupport.park();
      LockSupport.park();
      System.out.println(Thread.currentThread().getName() + "\t 被唤醒 " + System.currentTimeMillis());
    }, "[demo543] 线程1");
    t1.start();

    Thread t2 = new Thread(() -> {
      LockSupport.unpark(t1);
      LockSupport.unpark(t1);
      LockSupport.unpark(t1);
      LockSupport.unpark(t1);
      System.out.println(Thread.currentThread().getName() + "\t 发出通知");
    }, "[demo543] 线程2");
    t2.start();
  }
}


/*
线程无法被唤醒

[demo543] 线程2	 发出通知
[demo543] 线程1	 进入 1690245856563
 */
