package com.laioffer.第1部分_基础.第7章_辅助类.demo3;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 类描述: 6辆汽车🚗，停3个车位
 * <p>
 * created by 春夏秋冬在中南 on 2024/4/8 08:22
 */
public class SemaphoreDemo {

  public static void main(String[] args) {
    // 创建 Semaphore
    Semaphore semaphore = new Semaphore(3);

    // 模拟6辆汽车
    for (int i = 1; i <= 6; i++) {
      new Thread(() -> {
        // 抢到车位
        try {
          semaphore.acquire();
          System.out.println(Thread.currentThread().getName() + " 抢到了车位");

          // 设置随机的停车时间
          TimeUnit.SECONDS.sleep(new Random().nextInt(5));

          System.out.println(Thread.currentThread().getName() + " 离开了车位");
        } catch (Exception e) {
          throw new RuntimeException(e);
        } finally {
          // 释放
          semaphore.release();
        }
      }, String.valueOf(i)).start();
    }
  }
}

/*
  打印结果：
      5 抢到了车位
      3 抢到了车位
      1 抢到了车位
      5 离开了车位
      2 抢到了车位
      3 离开了车位
      6 抢到了车位
      2 离开了车位
      4 抢到了车位
      6 离开了车位
      1 离开了车位
      4 离开了车位
 */