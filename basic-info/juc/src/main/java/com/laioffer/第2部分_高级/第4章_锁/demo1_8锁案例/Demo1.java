package com.laioffer.第2部分_高级.第4章_锁.demo1_8锁案例;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: 案例1: 标准访问有ab两个线程，先打印邮件还是短信？
 * Created by 春夏秋冬在中南 on 2023/7/9 17:14
 */
public class Demo1 {
  public static void main(String[] args) {
    Phone phone = new Phone();
    new Thread(phone::sendEmail, "a").start();

    // 暂停几毫秒线程
    // 目的是为了保证线程a先启动
    try {
      TimeUnit.MILLISECONDS.sleep(300);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    new Thread(phone::sendSMS, "b").start();
  }
}

class Phone { // 资源类
  public synchronized void sendEmail() {
    System.out.println("send Email");
  }

  public synchronized void sendSMS() {
    System.out.println("send SMS");
  }
}

// send Email → send SMS

// 【synchronized锁的是资源类】
// 一个对象里面如果有多个synchronized方法，某一个时刻内，只要有一个线程去调用其中的一个synchronized方法了，
// 其它的线程都只能等待。即，某一个时刻内，只能有唯一的一个线程去访问这些synchronized方法。
// 锁的是当前对象this，被锁定后，其它的线程都不能进入到当前对象的其它synchronized方法
