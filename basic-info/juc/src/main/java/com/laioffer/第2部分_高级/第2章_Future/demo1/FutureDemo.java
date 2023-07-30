package com.laioffer.第2部分_高级.第2章_Future.demo1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * 类的描述: FutureTask的使用
 * Created by 春夏秋冬在中南 on 2023/7/6 08:07
 */
public class FutureDemo {

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    // line17~29: 多线程 & 异步
    RunnableFuture<String> ft = new FutureTask<>(new Thread1());
    Thread t1 = new Thread(ft, "t1");
    t1.start();

    // line22: 获取异步结果
    System.out.println("FutureTask的结果: " + ft.get());
  }
}

class Thread1 implements Callable<String> {

  @Override
  public String call() {
    System.out.println("进入 call() 方法");
    return "hello Callable";
  }
}

/*
目标: (1)多线程，(2)有返回值，(3)异步任务

Thread类的构造器只能传入Runnable类型的参数，Thread(Runnable target, String name)

FutureTask类的构造器 支持构造注入Callable，FutureTask(Callable callable)

 */