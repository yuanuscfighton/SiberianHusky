package com.laioffer.第2部分_高级.第2章_Future.demo5_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 类的描述: 使用延时方案解决"主线程结束后，CompletableFuture默认使用的线程池立刻关闭"的问题
 * Created by 春夏秋冬在中南 on 2023/8/5 10:04
 */
public class Demo256 {

  public static void main(String[] args) {
    Supplier<Integer> supplier = new Supplier<Integer>() {
      @Override
      public Integer get() {
        System.out.println(Thread.currentThread().getName() + " 进入[1]");
        int result = ThreadLocalRandom.current().nextInt(10);
        // 暂停几毫秒线程
        try {
          TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("1秒后出结果: " + result);
        return result;
      }
    };

    CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(supplier);
    completableFuture
        .whenComplete(new BiConsumer<Integer, Throwable>() {
          @Override
          public void accept(Integer val, Throwable throwable) {
            if (throwable == null) {
              System.out.println("计算完成，更新系统，UpdateValue=" + val);
            }
          }
        })
        .exceptionally(new Function<Throwable, Integer>() {
          @Override
          public Integer apply(Throwable throwable) {
            System.out.println("异常情况: " + throwable.getCause() + "\t" + throwable.getMessage());
            return null;
          }
        });

    System.out.println(Thread.currentThread().getName() + "线程先去忙其它任务");

    // 暂停几毫秒线程
    try {
      TimeUnit.MILLISECONDS.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

/*
  ForkJoinPool.commonPool-worker-19 进入[1]
  main线程先去忙其它任务
  1秒后出结果: 1
  计算完成，更新系统，UpdateValue=1
 */
