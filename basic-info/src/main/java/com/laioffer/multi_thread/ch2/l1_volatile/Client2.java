package com.laioffer.multi_thread.ch2.l1_volatile;

/**
 * 类的描述: 使用volatile
 * Created by 春夏秋冬在中南 on 2023/6/13 07:56
 */

// volatile的作用是强制从公共堆栈中获取变量的值，而不是从线程私有数据栈中取得变量的值

class RunThread extends Thread {
  private boolean isRunning = false;

  public boolean isRunning() {
    return isRunning;
  }

  public void setRunning(boolean isRunning) {
    this.isRunning = isRunning;
  }

  @Override
  public void run() {
    System.out.println("进入run了");
    while (isRunning) {

    }
    System.out.println("线程被停止了");
  }
}

public class Client2 {
  public static void main(String[] args) {
    try {
      RunThread thread = new RunThread();
      thread.start();
      Thread.sleep(1000);
      thread.setRunning(false);
      System.out.println("已经赋值为false");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
