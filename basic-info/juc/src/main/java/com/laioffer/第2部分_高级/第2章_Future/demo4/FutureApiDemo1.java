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
public class FutureApiDemo1 {

  public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
    Callable<String> call = new Callable<String>() {
      @Override
      public String call() {
        System.out.println(Thread.currentThread().getName() + "\t 进入耗时任务... \t\t" + System.currentTimeMillis());
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

    System.out.println(ft.get() + "\t\t" + System.currentTimeMillis());

    System.out.println(Thread.currentThread().getName() + "\t 忙其它任务了 \t\t" + System.currentTimeMillis());
  }
}

/*
t1	 进入耗时任务... 		1690697749540
task结束		            1690697754542
main	 忙其它任务了 		1690697754542
 */