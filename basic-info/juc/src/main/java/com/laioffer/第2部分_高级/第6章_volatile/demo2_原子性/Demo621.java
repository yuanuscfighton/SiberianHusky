package com.laioffer.第2部分_高级.第6章_volatile.demo2_原子性;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: 使用synchronized修饰，i++操作
 * Created by 春夏秋冬在中南 on 2023/8/22 07:42
 */
public class Demo621 {
  public static void main(String[] args) {
    Calculator c = new Calculator();

    for (int i = 0; i < 10; i++) {
      new Thread(() -> {
        for (int j = 0; j < 10000; j++) {
          c.plusPlus();
        }
      }, "线程1").start();
    }

    // 暂停几毫秒线程
    try {
      TimeUnit.MILLISECONDS.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("结果: " + c.number);
  }
}

class Calculator {
  int number;

  public synchronized void plusPlus() {
    number++;
  }
}

/*
打印:
     结果: 100000
 */