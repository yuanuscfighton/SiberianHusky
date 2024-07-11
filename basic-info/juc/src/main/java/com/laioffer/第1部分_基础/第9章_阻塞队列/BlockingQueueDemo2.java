package com.laioffer.第1部分_基础.第9章_阻塞队列;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo2 {

  public static void main(String[] args) {
    // 1.创建阻塞队列
    BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
    // 2.添加元素
    boolean res1 = blockingQueue.offer("d");
    System.out.println(res1); // true
    boolean res2 = blockingQueue.offer("e");
    System.out.println(res2); // true
    boolean res3 = blockingQueue.offer("f");
    System.out.println(res3); // true
    boolean res4 = blockingQueue.offer("g");
    System.out.println(res4); // false


    String res5 = blockingQueue.poll();
    System.out.println(res5); // d
    String res6 = blockingQueue.poll();
    System.out.println(res6); // e
    String res7 = blockingQueue.poll();
    System.out.println(res7); // f
    String res8 = blockingQueue.poll();
    System.out.println(res8); // null
  }
}
