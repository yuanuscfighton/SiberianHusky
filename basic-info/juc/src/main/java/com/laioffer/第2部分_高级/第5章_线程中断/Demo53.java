package com.laioffer.第2部分_高级.第5章_线程中断;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: 通过Thread类自带的中断api实例方法实现
 * Created by 春夏秋冬在中南 on 2023/7/24 08:00
 */
public class Demo53 {

  public static void main(String[] args) {
    Thread t1 = new Thread(() -> {
      while (true) {
        if (Thread.currentThread().isInterrupted()) {
          System.out.println(Thread.currentThread().getName() + "\t isInterrupted()返回为true，程序停止");
          break;
        }
        System.out.println("t1 ... ");
      }
    }, "线程1");
    t1.start();

    // 暂停几毫秒线程
    try {
      TimeUnit.MILLISECONDS.sleep(20);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    Thread t2 = new Thread(t1::interrupt, "线程2");
    t2.start();
  }
}

/*
1. isInterrupted：判断当前线程是否被中断(通过检查中断标志位)

2.interrupt：设置线程的中断状态为true，发起一个协商而不会立刻停止线程
 */


