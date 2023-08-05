package com.laioffer.第2部分_高级.第2章_Future.demo6_CompletableFutureApi;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 类的描述: CompletableFuture api 4: complete()
 * Created by 春夏秋冬在中南 on 2023/8/5 22:39
 */
public class Demo265 {

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
        return "def";
      }
    });

    // 暂停几毫秒线程
    try {
      TimeUnit.MILLISECONDS.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println(completableFuture.complete("completedFuture") + "\t" + completableFuture.join());
  }
}

/*
false	def

 */
