package com.laioffer.第2部分_高级.第2章_Future.demo5_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 类的描述: supplyAsync
 * Created by 春夏秋冬在中南 on 2023/8/5 09:44
 */
public class Demo254 {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
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

    System.out.println(Thread.currentThread().getName() + "线程先去忙其它任务了");
    System.out.println(completableFuture.get());
  }
}
