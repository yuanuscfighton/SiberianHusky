package com.laioffer.第2部分_高级.第3章_锁.demo1_8锁案例;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: 案例4: 有2部手机，先打印发邮件还是发短信？
 * Created by 春夏秋冬在中南 on 2023/7/9 21:00
 */
public class Demo4 {
  public static void main(String[] args) {
    Phone4 phone41 = new Phone4();
    Phone4 phone42 = new Phone4();

    new Thread(phone41::sendEmail, "a").start();

    // 暂停几毫秒线程
    try {
      TimeUnit.MILLISECONDS.sleep(300);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    new Thread(phone42::sendSMS, "b").start();
  }

}

class Phone4 {
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

/*
 打印结果: send SMS → send Email

 分析:
 资源类中 sendEmail()和sendSMS()确实加锁了，但线程a是给phone41加的锁，线程b是给phone42加的锁，是两个资源，不产生竞争
 */