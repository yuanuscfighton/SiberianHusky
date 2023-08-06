package com.laioffer.第2部分_高级.第3章_锁.demo1_8锁案例;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: 案例6: 有2个静态同步方法，有2部手机，先打印发邮件还是发短信？
 * Created by 春夏秋冬在中南 on 2023/7/9 21:16
 */
public class Demo6 {
  public static void main(String[] args) {
    Phone6 phone61 = new Phone6();
    Phone6 phone62 = new Phone6();

    new Thread(() -> {
      phone61.sendEmail();
    }, "a").start();

    // 暂停几毫秒线程
    try {
      TimeUnit.MILLISECONDS.sleep(300);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    new Thread(() -> {
      phone62.sendSMS();
    }, "b").start();
  }
}

class Phone6 {
  public static synchronized void sendEmail() {
    // 暂停几毫秒线程
    try {
      TimeUnit.MILLISECONDS.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("send Email");
  }

  public static synchronized void sendSMS() {
    System.out.println("send SMS");
  }
}

/*
打印结果: send Email → send SMS
 */
