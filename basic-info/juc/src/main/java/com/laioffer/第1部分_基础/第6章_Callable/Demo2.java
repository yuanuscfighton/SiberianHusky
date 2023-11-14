package com.laioffer.第1部分_基础.第6章_Callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 类的描述: 实现Callable接口
 * Created by 春夏秋冬在中南 on 2023/10/9 08:19
 */
public class Demo2 {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    FutureTask<String> futureTask = new FutureTask<>(new MyThread2());

    new Thread(futureTask, "FutureTask").start();

    while (!futureTask.isDone()) {
      System.out.println("wait......");
    }

    // 调用FutureTask的get()方法
    System.out.println(futureTask.get());
    System.out.println(Thread.currentThread().getName() + "结束");
  }

}

class MyThread2 implements Callable<String> {

  @Override
  public String call() {
    System.out.println(Thread.currentThread().getName() + "进入call方法");
    return "实现Callable接口";
  }
}

/*
打印结束
    wait......
    wait......
    wait......
    wait......
    wait......
    FutureTask进入call方法
    wait......
    wait......
    wait......
    实现Callable接口  ← get之前做了很多计算
    main结束
 */