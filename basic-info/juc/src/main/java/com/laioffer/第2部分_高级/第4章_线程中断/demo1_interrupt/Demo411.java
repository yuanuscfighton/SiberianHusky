package com.laioffer.第2部分_高级.第4章_线程中断.demo1_interrupt;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: 如何停止中断运行中的线程？通过一个volatile变量实现
 * Created by 春夏秋冬在中南 on 2023/7/20 23:18
 */
public class Demo411 {

  static volatile boolean isStop = false;

  public static void main(String[] args) {
    new Thread(() -> {
      while (true) {
        if (isStop) {
          System.out.println(Thread.currentThread().getName() + "\t \"isStop\" 被修改为true，程序停止运行");
          break;
        }
        System.out.println("线程1，线程运行中");
      }
    }, "线程1").start();

    // 暂停几毫秒线程
    try {
      TimeUnit.MILLISECONDS.sleep(20);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // 20秒后，启动线程2，设置中断标识位为true
    new Thread(() -> {
      isStop = true;
    }, "线程2").start();
  }
}

/*
线程1，线程运行中
线程1，线程运行中
... ... ... ...
线程1，线程运行中
线程1，线程运行中
线程1，线程运行中
线程1，线程运行中
线程1，线程运行中
线程1，线程运行中
线程1，线程运行中
线程1，线程运行中
线程1，线程运行中
线程1，线程运行中
线程1，线程运行中
线程1	 "isStop"被修改为true，程序停止运行
 */