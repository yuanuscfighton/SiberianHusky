package com.laioffer.multi_thread.demo2_synchronized;

/**
 * 类的描述: 测试
 * Created by 春夏秋冬在中南 on 2023/6/2 08:17
 */
public class Client {

  public static void main(String[] args) {
    // 两个线程同时访问一个没有同步的方法，如果两个线程同时操作业务对象中的实例变量，则有可能出现"非线程安全"的问题
    HasSelfPrivateNum numRef = new HasSelfPrivateNum();
    ThreadA threadA = new ThreadA(numRef);
    threadA.start();
    ThreadB threadB = new ThreadB(numRef);
    threadB.start();
  }
}
