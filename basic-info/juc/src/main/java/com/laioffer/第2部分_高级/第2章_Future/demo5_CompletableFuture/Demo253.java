package com.laioffer.第2部分_高级.第2章_Future.demo5_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 类的描述: runAsync(Runnable runnable, Executor executor)，自己指定线程池
 * Created by 春夏秋冬在中南 on 2023/7/9 14:41
 */
public class Demo253 {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    Supplier<String> runnable = new Supplier() {
      @Override
      public String get() {
        System.out.println(Thread.currentThread().getName());  // pool-1-thread-1
        // 暂停几毫秒线程
        try {
          TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return "hello supplyAsync";
      }
    };
    CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(runnable);
    System.out.println(completableFuture.get());
  }
}

/*
   ForkJoinPool.commonPool-worker-19
   hello supplyAsync  ← 返回异步任务处理后的结果
 */
