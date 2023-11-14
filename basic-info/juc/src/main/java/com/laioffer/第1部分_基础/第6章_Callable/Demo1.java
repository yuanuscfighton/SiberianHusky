package com.laioffer.第1部分_基础.第6章_Callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 类的描述: 实现Callable接口
 * Created by 春夏秋冬在中南 on 2023/10/9 08:19
 */
public class Demo1 {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    // 找一个类，既和Callable有关系，有和Runnable有关系
    // Runnable接口有实现了FutureTask，FutureTask构造可以传递Callable
    FutureTask<String> futureTask = new FutureTask<>(new MyThread1());

    new Thread(futureTask, "FutureTask").start();

    // 调用FutureTask的get()方法
    System.out.println(futureTask.get());
  }

}

class MyThread1 implements Callable<String> {

  @Override
  public String call() {
    System.out.println(Thread.currentThread().getName() + "进入call方法");
    return "实现Callable接口";
  }
}
