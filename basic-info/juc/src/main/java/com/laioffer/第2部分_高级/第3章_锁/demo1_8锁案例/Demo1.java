package com.laioffer.第2部分_高级.第3章_锁.demo1_8锁案例;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: 案例1: 标准访问有ab两个线程，先打印邮件还是短信？
 * Created by 春夏秋冬在中南 on 2023/7/9 17:14
 */
public class Demo1 {
  public static void main(String[] args) {
    // 【 线程 操作 资源类 】
    // 【 线程 操作 资源类 】

    // 1.创建资源
    Phone phone = new Phone();

    // 3.线程A操作资源
    Runnable actionA = phone::sendEmail;
    // 2.线程A
    new Thread(actionA, "a").start();

    ///////////////////////////////////////////////
    // 暂停几毫秒线程
    // 目的是为了保证线程a先启动
    try {
      TimeUnit.MILLISECONDS.sleep(300);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    ///////////////////////////////////////////////

    // 3.线程B操作资源
    Runnable actionB = phone::sendSMS;
    // 2.线程B
    new Thread(actionB, "b").start();
  }
}


/**
 * 资源类
 */
class Phone {
  public synchronized void sendEmail() {
    System.out.println("send Email");
  }

  public synchronized void sendSMS() {
    System.out.println("send SMS");
  }
}

/*
打印结果: send Email → send SMS

分析:
1. synchronized是悲观锁&独占锁
  加了synchronized后，有且只有一个锁可以进到资源类

2. synchronized锁的是资源类（当前对象）
  一个对象里面如果有多个synchronized方法，某一个时刻内，只要有一个线程去调用其中的一个synchronized方法了，
    其它的线程都只能等待。即，某一个时刻内，只能有唯一的一个线程去访问这些synchronized方法。
    锁的是当前对象this，被锁定后，其它的线程都不能进入到当前对象的其它synchronized方法

3. 对象锁
  a和b两个线程用的都是phone对象，即synchronized锁的是当前对象phone(i.e phone = new Phone()，等号右边new出来的Phone对象)
 */

