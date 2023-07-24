package com.laioffer.第2部分_高级.第5章_线程中断.demo1_interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 类的描述: 如何停止中断运行中的线程？通过AtomicBoolean
 * Created by 春夏秋冬在中南 on 2023/7/20 23:42
 */
public class Demo52 {

  static AtomicBoolean sAtomicBoolean = new AtomicBoolean(false);

  public static void main(String[] args) {

    new Thread(() -> {
      while (true) {
        if (sAtomicBoolean.get()) {
          System.out.println(Thread.currentThread().getName() + "\t sAtomicBoolean 被修改为true，程序停止运行");
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

    new Thread(() -> {
      sAtomicBoolean.set(true);
    }, "线程2").start();
  }
}
