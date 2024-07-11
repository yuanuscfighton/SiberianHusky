package com.laioffer.第1部分_基础.第9章_阻塞队列;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo1 {

  public static void main(String[] args) {
    // 1.创建阻塞队列
    BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
    // 2.添加元素
    boolean res1 = blockingQueue.add("a");
    System.out.println(res1); // true
    boolean res2 = blockingQueue.add("b");
    System.out.println(res2); // true
    boolean res3 = blockingQueue.add("c");
    System.out.println(res3); // true

    // 获取元素
    String e1 = blockingQueue.element();
    System.out.println(e1); // a

    // boolean res4 = blockingQueue.add("d");
    // Exception in thread "main" java.lang.IllegalStateException: Queue full
    // 	at java.base/java.util.AbstractQueue.add(AbstractQueue.java:98)
    // 	at java.base/java.util.concurrent.ArrayBlockingQueue.add(ArrayBlockingQueue.java:326)
    // 	at com.laioffer.第1部分_基础.第9章_阻塞队列.BlockingQueueDemo1.main(BlockingQueueDemo1.java:23)

    // 移除元素
    String res5 = blockingQueue.remove();
    System.out.println(res5); // a
    String res6 = blockingQueue.remove();
    System.out.println(res6); // b
    String res7 = blockingQueue.remove();
    System.out.println(res7); // c

    // String res8 = blockingQueue.remove();
    // System.out.println(res8);
    // Exception in thread "main" java.util.NoSuchElementException
    // 	at java.base/java.util.AbstractQueue.remove(AbstractQueue.java:117)
    // 	at com.laioffer.第1部分_基础.第9章_阻塞队列.BlockingQueueDemo1.main(BlockingQueueDemo1.java:36)

  }
}
