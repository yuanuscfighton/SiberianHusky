package com.laioffer.multi_thread.demo1_非线程安全;

/**
 * 类的描述: 实例变量非线程安全
 * Created by 春夏秋冬在中南 on 2023/6/2 08:09
 */
public class HasSelfPrivateNum {
  private int num = 0;

  public void addI(String username) {
    try {
      if (username.equals("a")) {
        num = 100;
        System.out.println("a set over");
        Thread.sleep(2000);
      } else {
        num = 200;
        System.out.println("b set over");
      }
      System.out.println(username + " num=" + num);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
