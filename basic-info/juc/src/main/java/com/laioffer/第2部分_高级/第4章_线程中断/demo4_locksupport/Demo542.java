package com.laioffer.第2部分_高级.第4章_线程中断.demo4_locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 类的描述:LockSupport中的park和unpark，先unpark，再park
 * Created by 春夏秋冬在中南 on 2023/7/25 08:34
 */
public class Demo542 {

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
      System.out.println(Thread.currentThread().getName() + "\t 被唤醒 " + System.currentTimeMillis());
    }, "[demo542] 线程1");
    t1.start();

    LockSupport.unpark(t1);
    System.out.println(Thread.currentThread().getName() + "\t 发出通知 " + System.currentTimeMillis());
  }
}


/*
sleep方法3秒后醒来，执行park无效，没有阻塞效果，
先执行了unpark(t1)导致上面的park方法形同虚设 <-- 打印出的时间戳是一样的

main	 发出通知 1690245453852
[demo542] 线程1	 进入 1690245456853
[demo542] 线程1	 被唤醒 1690245456853
 */
