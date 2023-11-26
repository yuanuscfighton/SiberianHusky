package com.laioffer.learn.ch2_对象及变量的并发访问.p3_volatile关键字.Demo1_可见性;

/**
 * 类的描述: volatile可见性 —— 使用多线程有可能出现死循环
 * <p>
 * Created by 春夏秋冬在中南 on 2023/11/12 22:56
 */
public class Demo233 {
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


class RunThread extends Thread {

  private boolean isRunning = true;

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
      // no-op
    }
    System.out.println("线程被停止了!");
  }
}

/*
打印结果：
    进入run了
    已经赋值为false
    ...   // 出现死循环

解释：
    1.在启动线程时，因为变量 private boolean isRunning = true;
    分别存储在公共内存及线程的私有内存中，

    线程运行后在线程的私有内存中取得isRunning的值一直是true，

    而代码 thread.setRunning(false); 虽然被执行，
    却是将公共内存中的 isRunning 变量改成false，操作的是两块内存地址中的数据，

    所以一直处于死循环的状态

    2.这个问题其实就是：私有内存中的值 和 公共内存中的值 不同步造成的
 */
