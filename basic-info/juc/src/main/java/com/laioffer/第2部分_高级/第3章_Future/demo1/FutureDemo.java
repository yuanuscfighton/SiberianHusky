package com.laioffer.第2部分_高级.第3章_Future.demo1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 类的描述: FutureTask的使用
 * Created by 春夏秋冬在中南 on 2023/7/6 08:07
 */
public class FutureDemo {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    FutureTask<String> ft = new FutureTask<>(new Thread1());
    Thread t1 = new Thread(ft, "t1");
    t1.start();

    System.out.println("FutureTask的结果: " + ft.get());
  }
}

class Thread1 implements Callable<String> {

  @Override
  public String call() {
    System.out.println("run in call()");
    return "hello Callable";
  }
}
