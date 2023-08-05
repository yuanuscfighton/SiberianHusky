package com.laioffer.第2部分_高级.第2章_Future.demo5_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 类的描述: 测试异常情况
 * Created by 春夏秋冬在中南 on 2023/8/5 10:18
 */
public class Demo258 {

  public static void main(String[] args) {
    ExecutorService threadPool1 = Executors.newFixedThreadPool(3);
    try {
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

          if (result > 5) {
            int tmp = 10 / 0;
          }
          return result;
        }
      };
      CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(supplier, threadPool1);
      completableFuture
          .whenComplete(new BiConsumer<Integer, Throwable>() {
            @Override
            public void accept(Integer val, Throwable throwable) {
              if (throwable == null) {
                System.out.println("计算完成，更新系统UpdateValue: " + val);
              }
            }
          })
          .exceptionally(new Function<Throwable, Integer>() {
            @Override
            public Integer apply(Throwable throwable) {
              throwable.printStackTrace();
              System.out.println("异常情况: " + throwable.getCause() + "\t" + throwable.getMessage());
              return null;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      threadPool1.shutdown();
    }
  }
}

/*
    pool-1-thread-1 进入[1]
    1秒后出结果: 9
    异常情况: java.lang.ArithmeticException: / by zero	java.lang.ArithmeticException: / by zero
    java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
      at java.base/java.util.concurrent.CompletableFuture.encodeThrowable(CompletableFuture.java:314)
      at java.base/java.util.concurrent.CompletableFuture.completeThrowable(CompletableFuture.java:319)
      at java.base/java.util.concurrent.CompletableFuture$AsyncSupply.run(CompletableFuture.java:1702)
      at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
      at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
      at java.base/java.lang.Thread.run(Thread.java:834)
    Caused by: java.lang.ArithmeticException: / by zero
      at com.laioffer.第2部分_高级.第2章_Future.demo5_CompletableFuture.Demo258$1.get(Demo258.java:36)
      at com.laioffer.第2部分_高级.第2章_Future.demo5_CompletableFuture.Demo258$1.get(Demo258.java:21)
      at java.base/java.util.concurrent.CompletableFuture$AsyncSupply.run(CompletableFuture.java:1700)
      ... 3 more
 */