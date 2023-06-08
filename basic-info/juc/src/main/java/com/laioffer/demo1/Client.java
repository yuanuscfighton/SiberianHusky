package com.laioffer.demo1;

/**
 * 类的描述:
 * Created by 春夏秋冬在中南 on 2023/6/4 01:41
 */
public class Client {
  public static void main(String[] args) {
    final ShareResource sr = new ShareResource();

    new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        try {
          sr.print5(i);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, "A").start();

    new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        try {
          sr.print10(i);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, "B").start();

    new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        try {
          sr.print15(i);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, "C").start();
  }
}
