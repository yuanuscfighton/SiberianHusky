package com.laioffer.第2部分_高级.第3章_锁.demo1_8锁案例;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: 案例8: 有1个静态同步方法，有1个普通同步方法，有2部手机，先打印发邮件还是发短信？
 * Created by 春夏秋冬在中南 on 2023/7/9 21:22
 */
public class Demo8 {
  public static void main(String[] args) {
    Phone8 phone81 = new Phone8();
    Phone8 phone82 = new Phone8();

    new Thread(() -> {
      phone81.sendEmail();
    }, "a").start();

    // 暂停几毫秒线程
    try {
      TimeUnit.MILLISECONDS.sleep(300);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    new Thread(phone82::sendSMS, "b").start();
  }
}

class Phone8 {
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

// send SMS → send Email