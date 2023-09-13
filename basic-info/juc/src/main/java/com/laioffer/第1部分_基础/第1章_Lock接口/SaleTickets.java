package com.laioffer.第1部分_基础.第1章_Lock接口;

/**
 * 类的描述: 3个售票员，卖30张票
 * Created by 春夏秋冬在中南 on 2023/9/11 08:25
 */
public class SaleTickets {
  public static void main(String[] args) {
    Ticket ticket = new Ticket();

    // 创建3个线程
    new Thread(() -> {
      for (int i = 0; i < 40; i++) {
        ticket.sale();
      }
    }, "线程1").start();

    new Thread(() -> {
      for (int i = 0; i < 40; i++) {
        ticket.sale();
      }
    }, "线程2").start();

    new Thread(() -> {
      for (int i = 0; i < 40; i++) {
        ticket.sale();
      }
    }, "线程3").start();
  }
}

class Ticket {
  private int number = 30;

  public synchronized void sale() {
    if (number > 0) {
      System.out.println(Thread.currentThread().getName() + "\t卖出: " + (number--) + " \t剩余: " + number);
    }
  }
}
