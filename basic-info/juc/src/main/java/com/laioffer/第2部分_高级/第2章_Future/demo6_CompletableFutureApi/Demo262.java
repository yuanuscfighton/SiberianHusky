package com.laioffer.第2部分_高级.第2章_Future.demo6_CompletableFutureApi;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 类的描述: CompletableFuture api 2: join()
 * Created by 春夏秋冬在中南 on 2023/8/5 22:22
 */
public class Demo262 {

  public static void main(String[] args) {
    CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
      @Override
      public String get() {
        // 暂停几毫秒线程
        try {
          TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return "abc";
      }
    });

    System.out.println(completableFuture.join());
  }
}
