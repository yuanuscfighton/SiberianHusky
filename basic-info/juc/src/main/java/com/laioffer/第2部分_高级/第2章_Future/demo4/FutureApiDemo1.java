package com.laioffer.第2部分_高级.第2章_Future.demo4;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 类的描述: Future # isDone
 * Created by 春夏秋冬在中南 on 2023/7/8 09:35
 */
public class FutureApiDemo1 {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
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

    while (true) {
      if (ft.isDone()) {
        System.out.println(ft.get());
        break;
      } else {
        try {
          TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("正在处理中...");
      }
    }
  }
}

// Future # isDone()缺店:
// 1.轮询的方式会消费无谓的CPU资源，而且也不见得能及时的拿到计算结果
// 2.如果想要异步获取结果，通常会以轮询的方式去获取结果，尽量不要阻塞

// 总结:
// Future对于结果的获取不是很友好，只能通过阻塞或者轮询的方式得到任务的结果