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

// 资源类
class Ticket {
  // 属性
  private int number = 30;

  // 方法
  public synchronized void sale() {
    if (number > 0) {
      System.out.println(Thread.currentThread().getName() + "\t卖出: " + (number--) + " \t剩余: " + number);
    }
  }
}

/*
    线程1	卖出: 30 	剩余: 29
    线程1	卖出: 29 	剩余: 28
    线程1	卖出: 28 	剩余: 27
    线程1	卖出: 27 	剩余: 26
    线程1	卖出: 26 	剩余: 25
    线程1	卖出: 25 	剩余: 24
    线程1	卖出: 24 	剩余: 23
    线程1	卖出: 23 	剩余: 22
    线程1	卖出: 22 	剩余: 21
    线程1	卖出: 21 	剩余: 20
    线程1	卖出: 20 	剩余: 19
    线程1	卖出: 19 	剩余: 18
    线程1	卖出: 18 	剩余: 17
    线程1	卖出: 17 	剩余: 16
    线程1	卖出: 16 	剩余: 15
    线程1	卖出: 15 	剩余: 14
    线程1	卖出: 14 	剩余: 13
    线程1	卖出: 13 	剩余: 12
    线程1	卖出: 12 	剩余: 11
    线程1	卖出: 11 	剩余: 10
    线程1	卖出: 10 	剩余: 9
    线程1	卖出: 9 	剩余: 8
    线程2	卖出: 8 	剩余: 7
    线程2	卖出: 7 	剩余: 6
    线程2	卖出: 6 	剩余: 5
    线程2	卖出: 5 	剩余: 4
    线程2	卖出: 4 	剩余: 3
    线程2	卖出: 3 	剩余: 2
    线程2	卖出: 2 	剩余: 1
    线程2	卖出: 1 	剩余: 0
 */