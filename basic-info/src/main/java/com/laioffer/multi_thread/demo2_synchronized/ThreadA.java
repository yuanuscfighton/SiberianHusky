package com.laioffer.multi_thread.demo2_synchronized;

/**
 * 类的描述: 线程A
 * Created by 春夏秋冬在中南 on 2023/6/2 08:15
 */
public class ThreadA extends Thread {
  private final HasSelfPrivateNum mNumRef;

  public ThreadA(HasSelfPrivateNum numRef) {
    super();
    mNumRef = numRef;
  }

  @Override
  public void run() {
    super.run();
    mNumRef.addI("a");
  }
}
