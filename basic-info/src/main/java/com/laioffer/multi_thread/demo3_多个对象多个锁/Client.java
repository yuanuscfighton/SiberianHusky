package com.laioffer.multi_thread.demo3_多个对象多个锁;

/**
 * 类的描述: 测试
 * Created by 春夏秋冬在中南 on 2023/6/2 08:17
 */
public class Client {

  public static void main(String[] args) {
    // 创建了2个HasSelfPrivateNum类的对象
    HasSelfPrivateNum numRef1 = new HasSelfPrivateNum();
    HasSelfPrivateNum numRef2 = new HasSelfPrivateNum();
    ThreadA threadA = new ThreadA(numRef1);
    threadA.start();
    ThreadB threadB = new ThreadB(numRef2);
    threadB.start();
  }
  /*
  - 上面demo是两个线程分别访问同一个类的两个不同实例的相同名称的同步方法
  - 由于创建了2个业务对象，在系统中产生了2个锁🔐
  - synchronized取到的锁都是对象锁，而不是把一段代码或方法当做锁，哪个线程先执行到带synchronized关键字的方法，
      哪个线程就持有该方法所属对象的锁Lock，那么其它线程只能呈等待状态，前提是多个线程访问的是同一个对象。
  - 如果多个线程访问多个对象，则JVM会创建多个锁，上面的demo就是创建了2个HasSelfPrivateNum类的对象，所以会产生2个锁

   */
}
