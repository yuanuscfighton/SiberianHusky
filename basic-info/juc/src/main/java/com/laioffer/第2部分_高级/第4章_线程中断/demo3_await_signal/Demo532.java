package com.laioffer.第2部分_高级.第4章_线程中断.demo3_await_signal;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类的描述: Lock中的await和signal方法，去掉lock.lock 和 lock.unlcok
 * Created by 春夏秋冬在中南 on 2023/7/25 07:19
 */
public class Demo532 {

  public static void main(String[] args) {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    new Thread(() -> {
      // lock.lock();
      try {
        System.out.println(Thread.currentThread().getName() + "\t [1]");
        condition.await();
        System.out.println(Thread.currentThread().getName() + "\t 被唤醒");
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        // lock.unlock();
      }
    }, "[线程531 线程1]").start();

    // 暂停几毫秒线程
    try {
      TimeUnit.MILLISECONDS.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    new Thread(() -> {
      // lock.lock();
      try {
        condition.signal();
        System.out.println(Thread.currentThread().getName() + "\t 发出通知");
      } finally {
        // lock.unlock();
      }
    }, "[线程531 线程2]").start();
  }
}

/*
Condition # await() 和 signal()方法，都必须在lock、unlock对里面，否则会抛出异常

[线程531 线程1]	 [1]
Exception in thread "[线程531 线程1]" java.lang.IllegalMonitorStateException
	at java.base/java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.addConditionWaiter(AbstractQueuedSynchronizer.java:1888)
	at java.base/java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2077)
	at com.laioffer.第2部分_高级.第5章_线程中断.demo3_await_signal.Demo532.lambda$main$0(Demo532.java:21)
	at java.base/java.lang.Thread.run(Thread.java:834)
Exception in thread "[线程531 线程2]" java.lang.IllegalMonitorStateException
	at java.base/java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.signal(AbstractQueuedSynchronizer.java:1981)
	at com.laioffer.第2部分_高级.第5章_线程中断.demo3_await_signal.Demo532.lambda$main$1(Demo532.java:40)
	at java.base/java.lang.Thread.run(Thread.java:834)

 */