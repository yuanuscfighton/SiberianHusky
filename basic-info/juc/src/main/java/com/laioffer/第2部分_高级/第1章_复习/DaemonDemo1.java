package com.laioffer.第2部分_高级.第1章_复习;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: 设置守护线程
 * Created by 春夏秋冬在中南 on 2023/7/1 17:29
 */
public class DaemonDemo1 {

  public static void main(String[] args) {
    // t1线程不做任何设置，默认是用户线程
    Thread t1 = new Thread(() -> {
      System.out.println(Thread.currentThread().getName() + "\t 开始运行, "
          + ((Thread.currentThread().isDaemon()) ? "守护线程" : "用户线程"));
      while (true) {
      }
    }, "t1");
    t1.setDaemon(true);
    t1.start();

    // 暂停几秒钟线程
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println(Thread.currentThread().getName() + "\t end主线程");

    /**
     * t1和main线程都是用户线程，二者相互独立，main线程结束了，t1不一定会结束，因为都是用户线程
     */
  }
}