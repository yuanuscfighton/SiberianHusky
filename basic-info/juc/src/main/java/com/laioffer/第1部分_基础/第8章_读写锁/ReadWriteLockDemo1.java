package com.laioffer.第1部分_基础.第8章_读写锁;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 类描述: 读写锁
 * <p>
 * created by 春夏秋冬在中南 on 2024/4/8 08:39
 */
public class ReadWriteLockDemo1 {
  public static void main(String[] args) {
    MyCache1 myCache = new MyCache1();
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
class MyCache1 {
  private volatile Map<String, Object> map = new HashMap<>();

  private ReadWriteLock rwLock = new ReentrantReadWriteLock();

  // 放数据
  public void put(String key, Object value) {
    // 添加读写锁
    rwLock.writeLock().lock();

    // 模拟写耗时，暂停一会儿
    try {
      System.out.println(Thread.currentThread().getName() + " 正在写操作 " + key);

      TimeUnit.MILLISECONDS.sleep(300);

      map.put(key, value);
      System.out.println(Thread.currentThread().getName() + " 写完了 " + key);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } finally {
      // 释放读写锁
      rwLock.writeLock().unlock();
    }
  }

  // 取数据
  public Object get(String key) {
    // 添加读锁
    rwLock.readLock().lock();

    // 模拟读耗时，暂停一会儿
    try {
      Object result;
      System.out.println(Thread.currentThread().getName() + " 正在读取操作 " + key);

      TimeUnit.MILLISECONDS.sleep(300);

      result = map.get(key);
      System.out.println(Thread.currentThread().getName() + " 读完了 " + key);
      return result;
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } finally {
      rwLock.readLock().unlock();
    }
  }
}

/*
  打印结果：
    2 正在写操作 2
    2 写完了 2
    1 正在写操作 1
    1 写完了 1
    3 正在写操作 3
    3 写完了 3
    5 正在写操作 5
    5 写完了 5
    4 正在写操作 4
    4 写完了 4
    1 正在读取操作 1
    2 正在读取操作 2
    3 正在读取操作 3
    4 正在读取操作 4
    5 正在读取操作 5
    4 读完了 4
    1 读完了 1
    3 读完了 3
    5 读完了 5
    2 读完了 2

 */
