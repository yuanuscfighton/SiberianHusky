package com.laioffer.第1部分_基础.第7章_辅助类.demo2;

import java.util.concurrent.CyclicBarrier;

/**
 * 类描述: 集齐7颗龙珠，可以召唤神龙
 * <p>
 * CyclicBarrier 允许一组线程互相等待，直到达到某个公共屏障点，在涉及一组固定大小的线程的程序中，这些线程必须不时的互相等待
 *
 * <p>
 * created by 春夏秋冬在中南 on 2024/4/8 08:10
 */
public class CyclicBarrierDemo {

  // 创建固定值
  private static final int NUMBER = 7;


  public static void main(String[] args) {
    /*
       CyclicBarrier 构造方法的 第1个参数是目标障碍数，每次执行 CyclicBarrier 一次后，障碍数会加1，如果达到了目标障碍数，才会执行 cyclicBarrier.await() 之后的语句
     */
    CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, () -> System.out.println("****** 集齐7颗龙珠，可以召唤神龙"));

    // 集齐7颗龙珠过程
    for (int i = 1; i <= NUMBER; i++) {
      new Thread(() -> {
        System.out.println(Thread.currentThread().getName() + "星龙被收集到了");
        try {
          cyclicBarrier.await();
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }, String.valueOf(i)).start();
    }
  }
}

/*
    打印结果：
        1星龙被收集到了
        3星龙被收集到了
        4星龙被收集到了
        2星龙被收集到了
        5星龙被收集到了
        6星龙被收集到了
        7星龙被收集到了
        ****** 集齐7颗龙珠，可以召唤神龙
 */