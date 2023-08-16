package com.laioffer.第2部分_高级.第4章_线程中断.demo2;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: 实例方法interrupt demo演示
 * Created by 春夏秋冬在中南 on 2023/8/12 01:24
 */
public class Demo421 {

  public static void main(String[] args) {
    Thread thread1 = new Thread(() -> {
      // 调用thread1.interrupt()方法后，仅仅是将中断标识位设置为true了，但不会打断for循环
      for (int i = 0; i < 100; i++) {
        System.out.println("当前i的值: " + i);
      }
      System.out.println("[1] t1线程调用interrupt()方法后的中断标识是: " + Thread.currentThread().isInterrupted());
    });
    thread1.start();

    System.out.println("t1线程默认的中断标识是: " + thread1.isInterrupted()); // 默认值是false

    // 暂停几毫秒线程
    try {
      TimeUnit.MILLISECONDS.sleep(2);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    thread1.interrupt(); // 此处将中断标识位置为true，所以下面打印出的结果就是true
    System.out.println("[2] t1线程调用interrupt()方法后的中断标识是: " + thread1.isInterrupted());
  }
}

/*
打印结果:
    t1线程默认的中断标识是: false
    当前i的值: 0
    当前i的值: 1
    当前i的值: 2
    当前i的值: 3
    当前i的值: 4
    当前i的值: 5
    当前i的值: 6
    当前i的值: 7
    当前i的值: 8
    当前i的值: 9
    当前i的值: 10
    当前i的值: 11
    当前i的值: 12
    当前i的值: 13
    当前i的值: 14
    当前i的值: 15
    当前i的值: 16
    当前i的值: 17
    当前i的值: 18
    当前i的值: 19
    当前i的值: 20
    当前i的值: 21
    当前i的值: 22
    当前i的值: 23
    当前i的值: 24
    当前i的值: 25
    当前i的值: 26
    当前i的值: 27
    当前i的值: 28
    当前i的值: 29
    当前i的值: 30
    当前i的值: 31
    当前i的值: 32
    当前i的值: 33
    当前i的值: 34
    当前i的值: 35
    当前i的值: 36
    当前i的值: 37
    当前i的值: 38
    当前i的值: 39
    当前i的值: 40
    当前i的值: 41
    当前i的值: 42
    当前i的值: 43
    当前i的值: 44
    当前i的值: 45
    当前i的值: 46
    当前i的值: 47
    当前i的值: 48
    当前i的值: 49
    当前i的值: 50
    当前i的值: 51
    当前i的值: 52
    当前i的值: 53
    当前i的值: 54
    当前i的值: 55
    当前i的值: 56
    当前i的值: 57
    当前i的值: 58
    当前i的值: 59
    当前i的值: 60
    当前i的值: 61
    当前i的值: 62
    [2] t1线程调用interrupt()方法后的中断标识是: true
    当前i的值: 63
    当前i的值: 64
    当前i的值: 65
    当前i的值: 66
    当前i的值: 67
    当前i的值: 68
    当前i的值: 69
    当前i的值: 70
    当前i的值: 71
    当前i的值: 72
    当前i的值: 73
    当前i的值: 74
    当前i的值: 75
    当前i的值: 76
    当前i的值: 77
    当前i的值: 78
    当前i的值: 79
    当前i的值: 80
    当前i的值: 81
    当前i的值: 82
    当前i的值: 83
    当前i的值: 84
    当前i的值: 85
    当前i的值: 86
    当前i的值: 87
    当前i的值: 88
    当前i的值: 89
    当前i的值: 90
    当前i的值: 91
    当前i的值: 92
    当前i的值: 93
    当前i的值: 94
    当前i的值: 95
    当前i的值: 96
    当前i的值: 97
    当前i的值: 98
    当前i的值: 99
    [1] t1线程调用interrupt()方法后的中断标识是: true

  小结:
      实例方法interrupt()仅仅是设置线程的中断状态位为true，不会停止线程
 */
