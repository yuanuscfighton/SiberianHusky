package com.laioffer.第2部分_高级.第2章_Future.demo5_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 类的描述: CompletableFuture中的whenComplete和exceptionally的使用
 * Created by 春夏秋冬在中南 on 2023/8/5 09:51
 */
public class Demo255 {

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
  }
}

/*
  ForkJoinPool.commonPool-worker-19 进入[1]
  main线程先去忙其它任务

  为什么没有打印「计算完成，更新系统，UpdateValue=」这句话呢？
  → 解释下为什么默认线程池关闭，自定义线程池记得关闭
  → 类似用户线程和守护线程，在这个demo中 ForkJoin线程池类似守护线程，如果main线程已经关掉了，就会把ForkJoin线程池关掉了
  → 解决方案: 主线程不要立刻结束，否则CompletableFuture默认使用的线程池就会立刻关闭，可以延时3秒钟
 */