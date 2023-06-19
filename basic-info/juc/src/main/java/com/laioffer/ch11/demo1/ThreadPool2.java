package com.laioffer.ch11.demo1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类的描述: 线程池三种常用分类: 可扩容的线程池
 * Created by 春夏秋冬在中南 on 2023/6/12 08:03
 */
public class ThreadPool2 {
  public static void main(String[] args) {
    ExecutorService threadPool = Executors.newCachedThreadPool();
    for (int i = 0; i < 10; i++) {
      threadPool.execute(() -> {
        System.out.println(Thread.currentThread().getName() + " 办理业务");
      });
    }
    threadPool.shutdown();
  }
}
