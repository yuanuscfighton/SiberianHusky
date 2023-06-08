package com.laioffer.demo3;

/**
 * 类的描述: 可重入锁
 * Created by 春夏秋冬在中南 on 2023/6/4 22:43
 */
public class SyncLockDemo1 {

  public synchronized void add() {
    add();
  }

  public static void main(String[] args) {
    new SyncLockDemo1().add();
  }
}
