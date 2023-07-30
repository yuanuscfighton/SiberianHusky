package com.laioffer.第2部分_高级.第2章_Future.demo4;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 类的描述: Future中的get方法
 * Created by 春夏秋冬在中南 on 2023/7/6 22:55
 */
public class FutureApiDemo {

  public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
    Callable<String> call = new Callable<String>() {
      @Override
      public String call() {
        System.out.println(Thread.currentThread().getName() + "\t [1]");
        try {
          TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return "task结束";
      }
    };

    FutureTask<String> ft = new FutureTask<>(call);
    Thread t1 = new Thread(ft, "t1");
    t1.start();
    System.out.println(Thread.currentThread().getName() + "\t 忙其它任务了");
    // System.out.println(ft.get());
    System.out.println(ft.get(3, TimeUnit.SECONDS));
  }
}

// Future # get()缺点:
// 一旦调用get()方法拿结果，如果计算没有完成容易导致程序阻塞
