package com.laioffer.第2部分_高级.第2章_Future.demo0;

import java.util.concurrent.Callable;

/**
 * 类的描述: 回顾Runnable和Callable接口
 * Created by 春夏秋冬在中南 on 2023/7/30 11:19
 */
public class Demo20 {
}

/*
实现Runnable接口，没有返回值
 */
class Thread1 implements Runnable {
  @Override
  public void run() {

  }
}

/*
实现Callable接口，有返回值
 */
class Thread2 implements Callable<String> {
  @Override
  public String call() throws Exception {
    return "Thread2";
  }
}
