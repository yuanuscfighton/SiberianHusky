package com.laioffer.第2部分_高级.第8章_CAS.demo1_没有使用CAS;

/**
 * 类的描述: 多线程环境下不使用原子类保证线程安全
 * Created by 春夏秋冬在中南 on 2023/7/29 10:08
 */
public class Demo81 {

  // 变量加volatile修饰，保证读取的时候，读到的是最新版本
  volatile int number = 0;

  // 读取
  public int getNumber() {
    return number;
  }

  // 写入加锁，保证原子性
  public synchronized void setNumber() {
    number++;
  }
}

/*
synchronized 是比较重的锁🔐
 */
