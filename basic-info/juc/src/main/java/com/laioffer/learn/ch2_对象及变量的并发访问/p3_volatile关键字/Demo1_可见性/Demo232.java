package com.laioffer.learn.ch2_对象及变量的并发访问.p3_volatile关键字.Demo1_可见性;

/**
 * 类的描述: volatile可见性 —— 使用多线程解决死循环
 * Created by 春夏秋冬在中南 on 2023/11/12 22:56
 */
public class Demo232 {
  public static void main(String[] args) {
    PrintString2 printString2 = new PrintString2();
    new Thread(printString2).start();

    System.out.println("我要停止它... stopThread=" + Thread.currentThread().getName());
    printString2.setContinuePrint(false);
  }
}

class PrintString2 implements Runnable {

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
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    printStringMethod();
  }
}