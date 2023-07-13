package com.laioffer.第2部分_高级.第4章_锁.demo1_8锁案例;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: 案例3: 增加一个普通的hello方法，先打印发邮件还是hello？
 * Created by 春夏秋冬在中南 on 2023/7/9 21:00
 */
public class Demo3 {
  public static void main(String[] args) {
    Phone3 phone3 = new Phone3();
    new Thread(phone3::sendEmail, "a").start();

    // 暂停几毫秒线程
    try {
      TimeUnit.MILLISECONDS.sleep(300);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    new Thread(phone3::hello, "b").start();
  }

}

class Phone3 {
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

  public void hello() {
    System.out.println("hello");
  }
}

// hello → send Email