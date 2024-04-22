package com.laioffer.第1部分_基础.第2章_线程间通信.demo1_sync;

/**
 * 类的描述: 线程间通信 : 虚假唤醒
 * <p>
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
解释虚假唤醒问题:
  现在有4个线程，AA,BB,CC,DD，其中，AA负责+1, BB负责-1, CC负责+1, DD负责-1

  AA    BB    CC    DD
  1
              等待


CC等待，其它线程都可能抢到锁，AA又抢到锁了，发现 number 不是0，所以等待，其它线程抢锁，CC抢到锁了，此时就会出现 number 超过1的情况

*/




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