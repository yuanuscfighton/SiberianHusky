package com.laioffer.第2部分_高级.第3章_Future.demo3;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 类的描述: 3个任务，开启多个异步任务线程来处理，总共耗时多少？
 * Created by 春夏秋冬在中南 on 2023/7/6 22:15
 */
public class FutureThreadPoolDemo {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ExecutorService threadPool = Executors.newFixedThreadPool(3);

    long startTimeMs = System.currentTimeMillis();

    // 任务1
    Callable<String> call1 = new Callable<String>() {
      @Override
      public String call() {
        try {
          TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return "task1 结束";
      }
    };
    FutureTask<String> ft1 = new FutureTask<>(call1);
    threadPool.submit(ft1);

    // 任务2
    Callable<String> call2 = new Callable<String>() {
      @Override
      public String call() {
        try {
          TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return "task2 结束";
      }
    };
    FutureTask<String> ft2 = new FutureTask<>(call2);
    threadPool.submit(ft2);

    System.out.println(ft1.get());
    System.out.println(ft2.get());

    // 任务3
    try {
      TimeUnit.MILLISECONDS.sleep(200);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    long endTimeMs = System.currentTimeMillis();
    System.out.println("总共耗时: " + (endTimeMs - startTimeMs) + "毫秒");
    System.out.println(Thread.currentThread().getName() + "\t end");

    threadPool.shutdown();
  }
}
