package com.laioffer.第2部分_高级.第4章_锁.demo1_8锁案例;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: 案例5: 有2个静态同步方法，有1部手机，先打印发邮件还是发短信？
 * Created by 春夏秋冬在中南 on 2023/7/9 21:11
 */
public class Demo5 {
  public static void main(String[] args) {
    Phone5 phone5 = new Phone5();
    new Thread(() -> {
      phone5.sendEmail();
    }, "a").start();

    // 暂停几毫秒线程
    try {
      TimeUnit.MILLISECONDS.sleep(300);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    new Thread(() -> {
      phone5.sendSMS();
    }, "b").start();
  }
}

class Phone5 {
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

// send Email → send SMS
// 对于普通同步方法，锁的是当前实例对象，通常指this，具体的一部部手机，所有的普通同步方法用的都是同一把锁，即，实例对象本身
// 对于静态同步方法，锁的是当前类的Class对象，如Phone.Class唯一的一个模版
// 对于同步方法块，锁的是synchronized括号内的对象