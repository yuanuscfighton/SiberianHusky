package com.laioffer.learn.ch2_对象及变量的并发访问.p3_volatile关键字.Demo1_可见性;

/**
 * 类的描述: volatile可见性 —— 单线程出现死循环
 * <p>
 * Created by 春夏秋冬在中南 on 2023/11/12 22:53
 */
public class Demo231 {
  public static void main(String[] args) {
    PrintString printStringService = new PrintString();
    printStringService.printStringMethod();

    System.out.println("我要停止它... stopThread=" + Thread.currentThread().getName());
    printStringService.setContinuePrint(false);
  }
}


class PrintString {
  private boolean isContinuePrint = true;

  public boolean isContinuePrint() {
    return isContinuePrint;
  }

  public void setContinuePrint(boolean isContinuePrint) {
    this.isContinuePrint = isContinuePrint;
  }

  public void printStringMethod() {
    try {
      while (isContinuePrint) {
        System.out.println("Run printStringMethod... 线程名=" + Thread.currentThread().getName());
        Thread.sleep(1000);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

/*
    停不下来的原因主要就是main线程一直在处理while()循环，
      导致程序不能继续执行后面的代码，解决的办法当然是用多线程技术
 */