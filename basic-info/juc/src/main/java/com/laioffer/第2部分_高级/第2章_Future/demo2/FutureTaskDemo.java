package com.laioffer.第2部分_高级.第2章_Future.demo2;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: 3个任务，目前只有一个线程main来处理，总共耗时多少？
 * Created by 春夏秋冬在中南 on 2023/7/6 22:29
 */
public class FutureTaskDemo {

  public static void main(String[] args) {
    long startTimeMs = System.currentTimeMillis();

    try {
      TimeUnit.MILLISECONDS.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      TimeUnit.MILLISECONDS.sleep(300);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      TimeUnit.MILLISECONDS.sleep(300);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    long endTimeMs = System.currentTimeMillis();
    System.out.println("总共耗时: " + (endTimeMs - startTimeMs) + "毫秒");
    System.out.println(Thread.currentThread().getName() + "\t  end");
  }
}

/*

总共耗时: 1108毫秒
main	  end

 */
