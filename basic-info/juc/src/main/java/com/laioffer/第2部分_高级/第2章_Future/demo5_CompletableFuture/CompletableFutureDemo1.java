package com.laioffer.第2部分_高级.第2章_Future.demo5_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 类的描述: runAsync没有指定线程池
 * Created by 春夏秋冬在中南 on 2023/7/9 11:27
 */
public class CompletableFutureDemo1 {
  public static void main(String[] args) throws ExecutionException, InterruptedException {

    Runnable action = new Runnable() {
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName()); // ForkJoinPool.commonPool-worker-19
        // 暂停几毫秒线程
        try {
          TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(action);
    System.out.println(completableFuture.get());
  }
}

// 由于使用的runAsync()方法只传入1个Runnable参数，使用的是默认的自带的线程池ForkJoinPool

