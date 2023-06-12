package com.laioffer.ch11.demo1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类的描述: 线程池三种常用分类: 一池五线程
 * Created by 春夏秋冬在中南 on 2023/6/12 07:57
 */
public class ThreadPool {
  public static void main(String[] args) {
    // 一个银行有5个窗口，模拟处理10个用户的请求
    ExecutorService threadPool = Executors.newFixedThreadPool(5);
    for (int i = 0; i < 10; i++) {
      threadPool.execute(() -> {
        System.out.println(Thread.currentThread().getName() + " 办理业务");
      });
    }
    threadPool.shutdown();
  }
}
