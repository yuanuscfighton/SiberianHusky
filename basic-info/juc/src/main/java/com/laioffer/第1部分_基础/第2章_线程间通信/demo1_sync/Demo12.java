package com.laioffer.第1部分_基础.第2章_线程间通信.demo1_sync;

/**
 * 类的描述: 线程间通信 : 虚假唤醒
 * Created by 春夏秋冬在中南 on 2023/9/17 16:46
 */
public class Demo12 {
  public static void main(String[] args) {
    Share12 share = new Share12();

    // 第3步：创建多个线程，调用资源类的操作方法
    new Thread(() -> {
      for (int i = 0; i < 100; i++) {
        try {
          share.increase();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }, "线程1").start();

    new Thread(() -> {
      for (int i = 0; i < 100; i++) {
        try {
          share.decrease();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }, "线程2").start();

    new Thread(() -> {
      for (int i = 0; i < 100; i++) {
        try {
          share.increase();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }, "线程3").start();

    new Thread(() -> {
      for (int i = 0; i < 100; i++) {
        try {
          share.decrease();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }, "线程4").start();
  }
}

// 第1步：创建资源类，在资源类中创建属性和操作方法
class Share12 {
  private int number = 0;

  public synchronized void increase() throws InterruptedException {
    // 第2步：在资源类操作方法中，判断 → 干活 → 通知
    if (number != 0) { // 解决方案 → if 改成 while
      // 等待
      this.wait(); /* 在哪里睡，就在哪里醒，继续向下执行，i.e.不会重新执行if条件 → 虚假唤醒 */
    }
    number++;
    System.out.println(Thread.currentThread().getName() + "::" + number);
    // 通知其它线程
    this.notifyAll();
  }

  public synchronized void decrease() throws InterruptedException {
    if (number != 1) {
      this.wait();
    }
    number--;
    System.out.println(Thread.currentThread().getName() + "::" + number);
    this.notifyAll();
  }
}

/*
打印结果：
    线程1::1
    线程2::0
    线程1::1
    线程2::0
    线程1::1
    线程2::0
    线程1::1
    线程2::0
    线程1::1
    线程4::0
    线程2::-1
    线程4::-2
    线程2::-3
    线程4::-4
    线程2::-5
    线程4::-6
    线程2::-7
    线程4::-8
    线程2::-9
    线程4::-10
    线程2::-11
    线程4::-12
    线程2::-13
    线程4::-14
    线程2::-15
    线程4::-16
    线程2::-17
    线程4::-18
    线程2::-19
    线程4::-20
    线程2::-21
    线程4::-22
    线程2::-23
    线程4::-24
    线程2::-25
    线程4::-26
    线程2::-27
    线程4::-28
    线程2::-29
 */