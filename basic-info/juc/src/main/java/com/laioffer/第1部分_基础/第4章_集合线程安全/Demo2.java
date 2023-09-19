package com.laioffer.第1部分_基础.第4章_集合线程安全;

import java.util.List;
import java.util.UUID;
import java.util.Vector;

/**
 * 类的描述: Vector - 线程安全
 * Created by 春夏秋冬在中南 on 2023/9/20 07:41
 */
public class Demo2 {
  public static void main(String[] args) {
    List<String> list = new Vector<>();
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

