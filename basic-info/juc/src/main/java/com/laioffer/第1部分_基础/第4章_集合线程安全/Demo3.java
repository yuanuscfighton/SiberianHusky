package com.laioffer.第1部分_基础.第4章_集合线程安全;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * 类的描述: Collections#synchronizedList - 线程安全
 * Created by 春夏秋冬在中南 on 2023/6/4 08:37
 */
public class Demo3 {
  public static void main(String[] args) {
    // List<String> list = new ArrayList<>();
    List<String> list = Collections.synchronizedList(new ArrayList<>());

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
