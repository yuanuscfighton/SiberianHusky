package com.laioffer.第2部分_高级.第2章_Future.demo5_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 类的描述: runAsync(Runnable runnable, Executor executor)，自己指定线程池
 * Created by 春夏秋冬在中南 on 2023/7/9 14:41
 */
public class Demo252 {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ExecutorService threadPool = Executors.newFixedThreadPool(3);
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName());  // pool-1-thread-1
        // 暂停几毫秒线程
        try {
          TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(runnable, threadPool);
    System.out.println(completableFuture.get());

    threadPool.shutdown();
  }
}

/*
    总结：如果没有指定Executor的方法，直接使用默认的ForkJoinPool.commonPool()作为它的线程池执行异步代码
      如果指定线程池，则使用我们自定义的或者特别指定的线程池执行异步代码
 */
