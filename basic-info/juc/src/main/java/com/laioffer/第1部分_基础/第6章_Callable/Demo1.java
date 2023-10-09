package com.laioffer.第1部分_基础.第6章_Callable;

import java.util.concurrent.Callable;

/**
 * 类的描述: 实现Callable接口
 * Created by 春夏秋冬在中南 on 2023/10/9 08:19
 */
public class Demo1 {

  public static void main(String[] args) {
    // 找一个类，既和Callable有关系，有和Runnable有关系
    // Runnable接口有实现了诶FutureTask，FutureTask构造可以传递Callable

  }

}

class MyThread implements Callable<String> {

  @Override
  public String call() {
    return "实现Callable接口";
  }
}
