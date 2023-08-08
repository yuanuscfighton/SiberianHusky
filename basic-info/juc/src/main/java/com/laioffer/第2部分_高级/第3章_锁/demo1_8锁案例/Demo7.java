package com.laioffer.第2部分_高级.第3章_锁.demo1_8锁案例;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: 案例7: 有1个静态同步方法，有1个普通同步方法，有1部手机，先打印发邮件还是发短信？
 * Created by 春夏秋冬在中南 on 2023/7/9 21:19
 */
public class Demo7 {
  public static void main(String[] args) {
    Phone7 phone7 = new Phone7();

    new Thread(() -> {
      phone7.sendEmail();
    }, "a").start();

    // 暂停几毫秒线程
    try {
      TimeUnit.MILLISECONDS.sleep(300);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    new Thread(phone7::sendSMS, "b").start();
  }
}

class Phone7 {
  public static synchronized void sendEmail() {
    // 暂停几毫秒线程
    try {
      TimeUnit.MILLISECONDS.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("send Email");
  }

  public synchronized void sendSMS() {
    System.out.println("send SMS");
  }
}

/*
 打印结果: send SMS → send Email

 分析:
 1. 当一个线程试图访问同步代码块时，它首先必须得到锁，正常退出或者抛出异常的时候，必须释放锁
      所有的普通同步方法用的都是同一把锁，即，实例对象本身，即，new出来的具体实例对象本身，本类this，
      也就是说如果一个实例对象的普通同步方法获取锁后，该实例对象的其它普通方法必须等待获取锁的方法释放锁后才能获取锁
 */


//
// 所有的镜头同步方法用的也是同一把锁，即，类对象本身，即，唯一模版Class
// 具体实例对象this和唯一模版Class，这两把锁是两个不同的对象，所以镜头同步方法与普通同步方法之间不会有竞争条件的
// 但是一旦一个静态同步方法获取锁后，其它的静态同步方法都必须等待该方法释放锁后才能获取同步锁。