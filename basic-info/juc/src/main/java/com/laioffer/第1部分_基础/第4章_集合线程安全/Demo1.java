package com.laioffer.第1部分_基础.第4章_集合线程安全;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 类的描述: ArrayList - 线程不安全
 * Created by 春夏秋冬在中南 on 2023/9/20 07:41
 */
public class Demo1 {
  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      new Thread(() -> {
        // 向集合中添加元素
        list.add(UUID.randomUUID().toString().substring(0, 8));

        // 从集合中获取元素
        System.out.println(list);
      }, String.valueOf(i)).start();
    }
  }
}

/*
    [d69f0e9d, 230b25d9]
    [d69f0e9d, 230b25d9, a7e01ac3, cf18af31, 4ba50aff, 1008bb38]
    [d69f0e9d, 230b25d9, a7e01ac3, cf18af31, 4ba50aff]
    [d69f0e9d, 230b25d9, a7e01ac3, cf18af31]
    [d69f0e9d, 230b25d9, a7e01ac3]
    [d69f0e9d, 230b25d9, a7e01ac3]
    [d69f0e9d, 230b25d9, a7e01ac3, cf18af31, 4ba50aff, 1008bb38, b986b2d1, d21c3366, 1892757b]
    [d69f0e9d, 230b25d9, a7e01ac3, cf18af31, 4ba50aff, 1008bb38, b986b2d1, d21c3366, 1892757b]
    [d69f0e9d, 230b25d9, a7e01ac3, cf18af31, 4ba50aff, 1008bb38, b986b2d1]
    Exception in thread "9" java.util.ConcurrentModificationException
        at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1043)
        at java.base/java.util.ArrayList$Itr.next(ArrayList.java:997)
        at java.base/java.util.AbstractCollection.toString(AbstractCollection.java:472)
        at java.base/java.lang.String.valueOf(String.java:2951)
        at java.base/java.io.PrintStream.println(PrintStream.java:897)
        at com.laioffer.第1部分_基础.第4章_集合线程安全.Demo1.lambda$main$0(Demo1.java:17)
        at java.base/java.lang.Thread.run(Thread.java:834)
 */
