package com.laioffer.learn.ch3_线程间通信.p1_wait_notify机制.demo1_不使用wait_notify通信;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述: 不使用wait/notify机制进行通信
 * <p>
 * created by 春夏秋冬在中南 on 2023/11/26 18:09
 */
public class TwoThreadTransData {
  // 使用sleep()结合while(true)死循环法来实现多个线程间通信
  public static void main(String[] args) {
    MyList service = new MyList();

    ThreadA a = new ThreadA(service);
    a.setName("A");
    a.start();

    ThreadB b = new ThreadB(service);
    b.setName("B");
    b.start();
  }
}


class ThreadA extends Thread {
  private MyList list;

  public ThreadA(MyList list) {
    super();
    this.list = list;
  }

  @Override
  public void run() {
    try {
      for (int i = 0; i < 10; i++) {
        list.add();
        System.out.println("添加了" + (i + 1) + "个元素");
        Thread.sleep(1000);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

class ThreadB extends Thread {
  private MyList list;

  public ThreadB(MyList list) {
    super();
    this.list = list;
  }

  @Override
  public void run() {
    try {
      while (true) {
        if (list.size() == 5) {
          System.out.println("==5了，线程b要退出了!");
          throw new InterruptedException();
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

class MyList {
  // 添加volatile关键字，以实现在A和B线程间的可视性
  volatile private List<String> list = new ArrayList<>();

  public void add() {
    list.add("张三");
  }

  public int size() {
    return list.size();
  }
}

/*
打印结果：
  添加了1个元素
  添加了2个元素
  添加了3个元素
  添加了4个元素
  添加了5个元素
  ==5了，线程b要退出了!
  java.lang.InterruptedException
    at com.laioffer.learn.ch3_线程间通信.p1_wait_notify机制.demo1_不使用wait_notify通信.ThreadB.run(TwoThreadTransData.java:63)
  添加了6个元素
  添加了7个元素
  添加了8个元素
  添加了9个元素
  添加了10个元素

分析：
    缺点：线程ThreadB.java不停地通过while语句轮询机制来检测某一个条件，这样会浪费CPU资源
 */