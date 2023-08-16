package com.laioffer.第2部分_高级.第4章_线程中断.demo2;

/**
 * 类的描述: Thread.interrupted()静态方法
 * Created by 春夏秋冬在中南 on 2023/8/13 17:46
 */
public class Demo426 {

  public static void main(String[] args) {
    System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
    System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
    System.out.println("[1]");
    Thread.currentThread().interrupt();
    System.out.println("[2]");
    System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
    System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
  }
}

/*
  main	false
  main	false
  [1]
  [2]
  main	true
  main	false


小结:
  Thread.interrupted()做了两件事情:
    (1) 判断当前线程是否已经被中断 → 返回当前线程的中断状态
    (2) 清除线程的中断状态 → 将当前线程的中断状态清零并重新设置为false
 */