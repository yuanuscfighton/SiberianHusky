package com.laioffer.第2部分_高级.第1章_复习;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: 设置守护线程
 * Created by 春夏秋冬在中南 on 2023/7/1 17:29
 */
public class DaemonDemo1 {

  public static void main(String[] args) {
    Thread t1 = new Thread(() -> {
      System.out.println(Thread.currentThread().getName() + "\t 开始运行, "
          + ((Thread.currentThread().isDaemon()) ? "守护线程" : "用户线程"));
      while (true) {
      }
    }, "t1");
    t1.setDaemon(true);
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
t1	 开始运行, 守护线程
main	 主线程-结束

小结:
 t1作为守护线程，守护着用户线程，用户线程结束了，守护线程也就结束了

 */