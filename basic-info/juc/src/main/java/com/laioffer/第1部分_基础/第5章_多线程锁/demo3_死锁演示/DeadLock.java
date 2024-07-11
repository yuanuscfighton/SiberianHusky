package com.laioffer.第1部分_基础.第5章_多线程锁.demo3_死锁演示;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: 死锁演示
 * <p>
 * Created by 春夏秋冬在中南 on 2023/10/8 22:42
 */
public class DeadLock {

  private static final Object o1 = new Object();
  private static final Object o2 = new Object();

  public static void main(String[] args) {
    new Thread(() -> {
      synchronized (o1) {
        System.out.println(Thread.currentThread().getName() + "持有锁o1，试图获取锁o2");

        // 暂停几毫秒线程 → 在线程2获取锁之前，线程1就执行完了。所以为了大概率出现死锁现象，这里sleep 1秒
        try {
          TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        synchronized (o2) {
          System.out.println(Thread.currentThread().getName() + "获取到锁o2");
        }
      }
    }, "线程1").start();

    new Thread(() -> {
      synchronized (o2) {
        System.out.println(Thread.currentThread().getName() + "持有锁o2，试图获取锁o1");

        // 暂停几毫秒线程
        try {
          TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        synchronized (o1) {
          System.out.println(Thread.currentThread().getName() + "获取到锁o1");
        }
      }
    }, "线程2").start();
  }
}

/*
    1.什么是死锁：有两个或者两个以上的进程正在执行过程中，因为争夺资源而造成的一种互相等待的现象。如果没有外力干涉，则无法继续执行下去
    2.产生死锁的原因
    （1）系统资源不足
    （2）进程运行推荐顺序不合适
    （3）资源分配不当
 */


/*
  打印：
    线程1持有锁o1，试图获取锁o2
    线程2持有锁o2，试图获取锁o1
    ... 程序没有停止⏹
 */

/*
    验证是否死锁
    （1）jps
    （2）jstack，jvm自带的堆栈跟踪工具

    操作步骤
    （第1步）>> jps
        输出：
          1157
          14918 Jps
          14119 GradleDaemon
          1159 Main
          14875 DeadLock

    （第2步）>> jstack 14875
        输出：
          2024-04-07 09:33:46
          Full thread dump Java HotSpot(TM) 64-Bit Server VM (11.0.14+8-LTS-263 mixed mode):

          Threads class SMR info:
          _java_thread_list=0x0000600000e4f5a0, length=13, elements={
          0x00007fab12824000, 0x00007fab12829000, 0x00007fab1501a800, 0x00007fab1282a000,
          0x00007fab1501b800, 0x00007fab1501c000, 0x00007fab1501d000, 0x00007fab12879000,
          0x00007fab139c9800, 0x00007fab12946800, 0x00007fab13ab3800, 0x00007fab14081000,
          0x00007fab15014800
          }

          "Reference Handler" #2 daemon prio=10 os_prio=31 cpu=0.10ms elapsed=27.70s tid=0x00007fab12824000 nid=0x4d03 waiting on condition  [0x000000030a32c000]
             java.lang.Thread.State: RUNNABLE
                  at java.lang.ref.Reference.waitForReferencePendingList(java.base@11.0.14/Native Method)
                  at java.lang.ref.Reference.processPendingReferences(java.base@11.0.14/Reference.java:241)
                  at java.lang.ref.Reference$ReferenceHandler.run(java.base@11.0.14/Reference.java:213)

          "Finalizer" #3 daemon prio=8 os_prio=31 cpu=0.41ms elapsed=27.70s tid=0x00007fab12829000 nid=0x5503 in Object.wait()  [0x000000030a42f000]
             java.lang.Thread.State: WAITING (on object monitor)
                  at java.lang.Object.wait(java.base@11.0.14/Native Method)
                  - waiting on <0x000000061fe09018> (a java.lang.ref.ReferenceQueue$Lock)
                  at java.lang.ref.ReferenceQueue.remove(java.base@11.0.14/ReferenceQueue.java:155)
                  - waiting to re-lock in wait() <0x000000061fe09018> (a java.lang.ref.ReferenceQueue$Lock)
                  at java.lang.ref.ReferenceQueue.remove(java.base@11.0.14/ReferenceQueue.java:176)
                  at java.lang.ref.Finalizer$FinalizerThread.run(java.base@11.0.14/Finalizer.java:170)

          "Signal Dispatcher" #4 daemon prio=9 os_prio=31 cpu=0.43ms elapsed=27.68s tid=0x00007fab1501a800 nid=0x7803 runnable  [0x0000000000000000]
             java.lang.Thread.State: RUNNABLE

          "Service Thread" #5 daemon prio=9 os_prio=31 cpu=0.04ms elapsed=27.68s tid=0x00007fab1282a000 nid=0x5d03 runnable  [0x0000000000000000]
             java.lang.Thread.State: RUNNABLE

          "C2 CompilerThread0" #6 daemon prio=9 os_prio=31 cpu=66.59ms elapsed=27.68s tid=0x00007fab1501b800 nid=0x5f03 waiting on condition  [0x0000000000000000]
             java.lang.Thread.State: RUNNABLE
             No compile task

          "C1 CompilerThread0" #9 daemon prio=9 os_prio=31 cpu=45.69ms elapsed=27.68s tid=0x00007fab1501c000 nid=0x6103 waiting on condition  [0x0000000000000000]
             java.lang.Thread.State: RUNNABLE
             No compile task

          "Sweeper thread" #10 daemon prio=9 os_prio=31 cpu=0.04ms elapsed=27.68s tid=0x00007fab1501d000 nid=0x7303 runnable  [0x0000000000000000]
             java.lang.Thread.State: RUNNABLE

          "Common-Cleaner" #11 daemon prio=8 os_prio=31 cpu=0.10ms elapsed=27.62s tid=0x00007fab12879000 nid=0x6603 in Object.wait()  [0x000000030abd9000]
             java.lang.Thread.State: TIMED_WAITING (on object monitor)
                  at java.lang.Object.wait(java.base@11.0.14/Native Method)
                  - waiting on <0x000000061ff88768> (a java.lang.ref.ReferenceQueue$Lock)
                  at java.lang.ref.ReferenceQueue.remove(java.base@11.0.14/ReferenceQueue.java:155)
                  - waiting to re-lock in wait() <0x000000061ff88768> (a java.lang.ref.ReferenceQueue$Lock)
                  at jdk.internal.ref.CleanerImpl.run(java.base@11.0.14/CleanerImpl.java:148)
                  at java.lang.Thread.run(java.base@11.0.14/Thread.java:834)
                  at jdk.internal.misc.InnocuousThread.run(java.base@11.0.14/InnocuousThread.java:134)

          "Monitor Ctrl-Break" #12 daemon prio=5 os_prio=31 cpu=14.23ms elapsed=27.46s tid=0x00007fab139c9800 nid=0x6d03 runnable  [0x000000030acdc000]
             java.lang.Thread.State: RUNNABLE
                  at java.net.SocketInputStream.socketRead0(java.base@11.0.14/Native Method)
                  at java.net.SocketInputStream.socketRead(java.base@11.0.14/SocketInputStream.java:115)
                  at java.net.SocketInputStream.read(java.base@11.0.14/SocketInputStream.java:168)
                  at java.net.SocketInputStream.read(java.base@11.0.14/SocketInputStream.java:140)
                  at sun.nio.cs.StreamDecoder.readBytes(java.base@11.0.14/StreamDecoder.java:284)
                  at sun.nio.cs.StreamDecoder.implRead(java.base@11.0.14/StreamDecoder.java:326)
                  at sun.nio.cs.StreamDecoder.read(java.base@11.0.14/StreamDecoder.java:178)
                  - locked <0x000000061fa11ef0> (a java.io.InputStreamReader)
                  at java.io.InputStreamReader.read(java.base@11.0.14/InputStreamReader.java:181)
                  at java.io.BufferedReader.fill(java.base@11.0.14/BufferedReader.java:161)
                  at java.io.BufferedReader.readLine(java.base@11.0.14/BufferedReader.java:326)
                  - locked <0x000000061fa11ef0> (a java.io.InputStreamReader)
                  at java.io.BufferedReader.readLine(java.base@11.0.14/BufferedReader.java:392)
                  at com.intellij.rt.execution.application.AppMainV2$1.run(AppMainV2.java:55)

          "线程1" #13 prio=5 os_prio=31 cpu=2.90ms elapsed=27.45s tid=0x00007fab12946800 nid=0x8103 waiting for monitor entry  [0x000000030aee2000]
             java.lang.Thread.State: BLOCKED (on object monitor)
                  at com.laioffer.第1部分_基础.第5章_多线程锁.demo3_死锁演示.DeadLock.lambda$main$0(DeadLock.java:27)
                  - waiting to lock <0x000000061fdf8078> (a java.lang.Object)
                  - locked <0x000000061fdf8068> (a java.lang.Object)
                  at com.laioffer.第1部分_基础.第5章_多线程锁.demo3_死锁演示.DeadLock$$Lambda$14/0x0000000800066840.run(Unknown Source)
                  at java.lang.Thread.run(java.base@11.0.14/Thread.java:834)

          "线程2" #14 prio=5 os_prio=31 cpu=3.59ms elapsed=27.45s tid=0x00007fab13ab3800 nid=0x820b waiting for monitor entry  [0x000000030afe5000]
             java.lang.Thread.State: BLOCKED (on object monitor)
                  at com.laioffer.第1部分_基础.第5章_多线程锁.demo3_死锁演示.DeadLock.lambda$main$1(DeadLock.java:44)
                  - waiting to lock <0x000000061fdf8068> (a java.lang.Object)
                  - locked <0x000000061fdf8078> (a java.lang.Object)
                  at com.laioffer.第1部分_基础.第5章_多线程锁.demo3_死锁演示.DeadLock$$Lambda$16/0x0000000800066040.run(Unknown Source)
                  at java.lang.Thread.run(java.base@11.0.14/Thread.java:834)

          "DestroyJavaVM" #15 prio=5 os_prio=31 cpu=216.88ms elapsed=27.44s tid=0x00007fab14081000 nid=0x2a03 waiting on condition  [0x0000000000000000]
             java.lang.Thread.State: RUNNABLE

          "Attach Listener" #16 daemon prio=9 os_prio=31 cpu=0.40ms elapsed=0.11s tid=0x00007fab15014800 nid=0xa303 waiting on condition  [0x0000000000000000]
             java.lang.Thread.State: RUNNABLE

          "VM Thread" os_prio=31 cpu=4.43ms elapsed=27.71s tid=0x00007fab15008800 nid=0x4703 runnable

          "GC Thread#0" os_prio=31 cpu=2.41ms elapsed=27.72s tid=0x00007fab13810000 nid=0x3703 runnable

          "G1 Main Marker" os_prio=31 cpu=0.39ms elapsed=27.72s tid=0x00007fab1384e000 nid=0x3603 runnable

          "G1 Conc#0" os_prio=31 cpu=0.03ms elapsed=27.72s tid=0x00007fab1384e800 nid=0x4103 runnable

          "G1 Refine#0" os_prio=31 cpu=0.21ms elapsed=27.72s tid=0x00007fab13977800 nid=0x4203 runnable

          "G1 Young RemSet Sampling" os_prio=31 cpu=4.38ms elapsed=27.72s tid=0x00007fab13978000 nid=0x4303 runnable
          "VM Periodic Task Thread" os_prio=31 cpu=20.20ms elapsed=27.46s tid=0x00007fab1294e800 nid=0x8067 waiting on condition

          JNI global refs: 16, weak refs: 0


          Found one Java-level deadlock:
          =============================
          "线程1":
            waiting to lock monitor 0x00007faaf95a7f00 (object 0x000000061fdf8078, a java.lang.Object),
            which is held by "线程2"
          "线程2":
            waiting to lock monitor 0x00007faaf95a7e00 (object 0x000000061fdf8068, a java.lang.Object),
            which is held by "线程1"

          Java stack information for the threads listed above:
          ===================================================
          "线程1":
                  at com.laioffer.第1部分_基础.第5章_多线程锁.demo3_死锁演示.DeadLock.lambda$main$0(DeadLock.java:27)
                  - waiting to lock <0x000000061fdf8078> (a java.lang.Object)
                  - locked <0x000000061fdf8068> (a java.lang.Object)
                  at com.laioffer.第1部分_基础.第5章_多线程锁.demo3_死锁演示.DeadLock$$Lambda$14/0x0000000800066840.run(Unknown Source)
                  at java.lang.Thread.run(java.base@11.0.14/Thread.java:834)
          "线程2":
                  at com.laioffer.第1部分_基础.第5章_多线程锁.demo3_���锁演示.DeadLock.lambda$main$1(DeadLock.java:44)
                  - waiting to lock <0x000000061fdf8068> (a java.lang.Object)
                  - locked <0x000000061fdf8078> (a java.lang.Object)
                  at com.laioffer.第1部分_基础.第5章_多线程锁.demo3_死锁演示.DeadLock$$Lambda$16/0x0000000800066040.run(Unknown Source)
                  at java.lang.Thread.run(java.base@11.0.14/Thread.java:834)

          Found 1 deadlock.

 */