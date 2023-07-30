package com.laioffer.第2部分_高级.第1章_复习;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: 守护线程
 * Created by 春夏秋冬在中南 on 2023/7/1 17:23
 */
public class DaemonDemo {

  public static void main(String[] args) {
    // t1线程不做任何设置，默认是用户线程
    Thread t1 = new Thread(() -> {
      System.out.println(Thread.currentThread().getName() + "\t 开始运行, "
          + ((Thread.currentThread().isDaemon()) ? "守护线程" : "用户线程"));
      while (true) {
      }
    }, "t1");

    t1.start();

    // 暂停几毫秒线程
    try {
      TimeUnit.MILLISECONDS.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread().getName() + "\t 主线程-结束");
  }
}

/*
t1	 开始运行, 用户线程
main	 主线程-结束

小结:
(1) 此时程序没有结束，因为t1和main线程都是用户线程，二者相互独立，main线程结束了，t1线程不一定会结束
(2) t1没有特殊设置，默认都是用户线程

 */