package com.laioffer.multi_thread.ch2.l1_volatile;

/**
 * 类的描述: 解决同步死循环
 * Created by 春夏秋冬在中南 on 2023/6/13 07:53
 */
class PrintString1 implements Runnable {

  private boolean isContinuePrint = true;

  public boolean isContinuePrint() {
    return isContinuePrint;
  }

  public void setContinuePrint(boolean isContinuePrint) {
    this.isContinuePrint = isContinuePrint;
  }

  public void printStringMethod() {
    while (isContinuePrint) {
      try {
        System.out.println("run printStringMethod threadName=" + Thread.currentThread().getName());
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void run() {
    printStringMethod();
  }
}

public class Client1 {
  public static void main(String[] args) {
    PrintString1 service = new PrintString1();
    new Thread(service).start();
    System.out.println("我要停止它! stopThread=" + Thread.currentThread().getName());
    service.setContinuePrint(false);
  }
}
