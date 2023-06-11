package com.laioffer.ch5.demo1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 类的描述:
 * Created by 春夏秋冬在中南 on 2023/6/10 22:27
 */
class ThreadA implements Callable<Integer> {
  @Override
  public Integer call() throws Exception {
    System.out.println(Thread.currentThread().getName() + " in callable");
    return 200;
  }
}

public class Client {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    FutureTask<Integer> futureTask1 = new FutureTask<>(new ThreadA());
    // 创建一个线程
    new Thread(futureTask1, "ThreadA").start();
    // 调用FutureTask的get()方法
    System.out.println(futureTask1.get());
  }
}