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
        System.out.println(Thread.currentThread().getName() + "\t 进入耗时任务... \t\t" + System.currentTimeMillis());
        // 暂停几毫秒线程
        try {
          TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return "task结束";
      }
    };

    FutureTask<String> ft = new FutureTask<>(call);
    Thread t1 = new Thread(ft, "t1");
    t1.start();

    System.out.println(Thread.currentThread().getName() + "\t 忙其它任务了 \t\t" + System.currentTimeMillis());

    System.out.println(ft.get() + "\t\t" + System.currentTimeMillis());
  }
}

/*
main	 忙其它任务了 		1690697585160
t1	 进入耗时任务... 		1690697585160
task结束		            1690697590164

一旦调用 FutureTask # get()方法拿结果，如果计算没有完成容易导致程序阻塞
 */
