package com.laioffer.第1部分_基础.第8章_读写锁;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 类描述: 读写锁
 * <p>
 * created by 春夏秋冬在中南 on 2024/4/8 08:39
 */
public class ReadWriteLockDemo {
  public static void main(String[] args) {
    MyCache myCache = new MyCache();
    // 创建线程放数据
    for (int i = 1; i <= 5; i++) {
      final int num = i;
      new Thread(() -> {
        myCache.put(String.valueOf(num), String.valueOf(num));
      }, String.valueOf(i)).start();
    }

    // 创建线程读数据
    for (int i = 1; i <= 5; i++) {
      final int num = i;
      new Thread(() -> {
        myCache.get(String.valueOf(num));
      }, String.valueOf(i)).start();
    }
  }
}

/**
 * 资源类
 */
class MyCache {
  private volatile Map<String, Object> map = new HashMap<>();

  // 放数据
  public void put(String key, Object value) {
    System.out.println(Thread.currentThread().getName() + " 正在写操作 " + key);

    // 模拟写耗时，暂停一会儿
    try {
      TimeUnit.MILLISECONDS.sleep(300);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    map.put(key, value);
    System.out.println(Thread.currentThread().getName() + " 写完了 " + key);
  }

  // 取数据
  public Object get(String key) {
    Object result;
    System.out.println(Thread.currentThread().getName() + " 正在读取操作 " + key);

    // 模拟读耗时，暂停一会儿
    try {
      TimeUnit.MILLISECONDS.sleep(300);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    result = map.get(key);
    System.out.println(Thread.currentThread().getName() + " 读完了 " + key);
    return result;
  }
}

/*
  打印结果：
      1 正在写操作 1
      2 正在写操作 2
      5 正在写操作 5
      4 正在写操作 4
      3 正在写操作 3
      1 正在读取操作 1
      2 正在读取操作 2
      3 正在读取操作 3
      4 正在读取操作 4
      5 正在读取操作 5
      4 写完了 4
      5 读完了 5
      1 写完了 1
      1 读完了 1
      3 读完了 3
      2 写完了 2
      3 写完了 3
      2 读完了 2
      5 写完了 5
      4 读完了 4

   问题：没有写完就开始读了
 */
