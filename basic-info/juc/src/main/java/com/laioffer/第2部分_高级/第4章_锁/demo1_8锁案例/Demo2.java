package com.laioffer.第2部分_高级.第4章_锁.demo1_8锁案例;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: 案例2: sendEmail方法中加入3秒延时，先打印邮件还是短信？
 * Created by 春夏秋冬在中南 on 2023/7/9 17:30
 */
public class Demo2 {
  public static void main(String[] args) {
    Phone2 phone2 = new Phone2();

    new Thread(phone2::sendEmail, "a").start();

    // 暂停几毫秒线程
    try {
      TimeUnit.MILLISECONDS.sleep(300);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    new Thread(phone2::sendSMS, "b").start();
  }
}

class Phone2 {
  public synchronized void sendEmail() {
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

// 先打印"邮件"，再打印"短信"
