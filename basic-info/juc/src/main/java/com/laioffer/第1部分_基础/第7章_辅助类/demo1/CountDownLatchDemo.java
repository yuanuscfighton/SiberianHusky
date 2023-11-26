package com.laioffer.第1部分_基础.第7章_辅助类.demo1;

/**
 * 类的描述: 6个同学都离开教室后，班长才能锁门
 * <p>
 * Created by 春夏秋冬在中南 on 2023/11/15 08:19
 */
public class CountDownLatchDemo {

  public static void main(String[] args) {
    for (int i = 0; i < 6; i++) {
      new Thread(() -> {
        System.out.println(Thread.currentThread().getName() + "号同学离开了教室");
      }, String.valueOf(i)).start();
    }

    System.out.println(Thread.currentThread().getName() + "... 班长锁门走人了");
  }
}

/*
打印结果：
    0号同学离开了教室
    1号同学离开了教室
    3号同学离开了教室
    2号同学离开了教室
    4号同学离开了教室
    main... 班长锁门走人了
    5号同学离开了教室    ← 被锁在教室里了
 */