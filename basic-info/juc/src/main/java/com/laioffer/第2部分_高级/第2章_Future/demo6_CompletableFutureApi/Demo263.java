package com.laioffer.第2部分_高级.第2章_Future.demo6_CompletableFutureApi;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 类的描述: CompletableFuture api 3: getNow()
 * Created by 春夏秋冬在中南 on 2023/8/5 22:24
 */
public class Demo263 {

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

    System.out.println(completableFuture.getNow("xxxx"));
  }
}

/*
xxxx

没有计算完成的情况下，输出一个替代的结果

CompletableFuture # getNow() 立即获取结果，不阻塞
    计算完成: 返回计算后的结果
    没算完:  返回设定的valueIfAbsent值
 */
