package com.laioffer.第1部分_基础.第4章_集合线程安全;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 类的描述: CopyOnWriteArrayList - 线程安全
 * Created by 春夏秋冬在中南 on 2023/9/20 07:51
 */
public class Demo4 {
  public static void main(String[] args) {
    /*
      写时复制
     */
    List<String> list = new CopyOnWriteArrayList<>();

    for (int i = 0; i < 30; i++) {
      new Thread(() -> {
        // 添加元素
        list.add(UUID.randomUUID().toString().substring(0, 8));
        // 从集合中获取元素
        System.out.println(list);
      }, String.valueOf(i)).start();
    }
  }
}
