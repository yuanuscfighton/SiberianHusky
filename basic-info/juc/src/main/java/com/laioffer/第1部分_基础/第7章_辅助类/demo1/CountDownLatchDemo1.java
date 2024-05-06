package com.laioffer.第1部分_基础.第7章_辅助类.demo1;

import java.util.concurrent.CountDownLatch;

/**
 * 类的描述: 使用CountDownLatch解决问题
 * <p>
 * Created by 春夏秋冬在中南 on 2023/11/15 08:23
 */
public class CountDownLatchDemo1 {
  public static void main(String[] args) throws InterruptedException {

    // 创建CountDownLatch对象，设置初始值
    CountDownLatch countDownLatch = new CountDownLatch(6);

    for (int i = 0; i < 6; i++) {
      new Thread(() -> {
        System.out.println(Thread.currentThread().getName() + "号同学离开了教室");

        // 计数 -1
        countDownLatch.countDown(); // 调用 countdown 方法的线程不会阻塞
      }, String.valueOf(i)).start();
    }

    // 等待
    countDownLatch.await(); // 当一个或多个线程调用 await 方法时，这些线程会阻塞

    System.out.println(Thread.currentThread().getName() + "... 班长离开教室");
  }
}

/*
打印结果：
    0号同学离开了教室
    1号同学离开了教室
    2号同学离开了教室
    4号同学离开了教室
    3号同学离开了教室
    5号同学离开了教室
    main... 班长离开教室
 */