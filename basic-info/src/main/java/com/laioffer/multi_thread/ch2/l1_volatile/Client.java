package com.laioffer.multi_thread.ch2.l1_volatile;

/**
 * 类的描述: volatile与死循环
 * Created by 春夏秋冬在中南 on 2023/6/13 07:46
 */
class PrintString {

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
}

public class Client {
  public static void main(String[] args) {
    PrintString service = new PrintString();
    service.printStringMethod();
    System.out.println("我要停止它! stopThread=" + Thread.currentThread().getName());
    service.setContinuePrint(false);
    // 停不下来的原因是main线程一直在处理while()循环，导致程序不能执行后面的代码
    // 解决方案: 使用多线程技术
  }
}
