package com.laioffer.第2部分_高级.第4章_锁.demo5_死锁;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: 死锁案例
 * Created by 春夏秋冬在中南 on 2023/7/19 07:37
 */
public class Demo51 {


  public static void main(String[] args) {
    final Object objectA = new Object();
    final Object objectB = new Object();

    new Thread(() -> {
      synchronized (objectA) {
        System.out.println(Thread.currentThread().getName() + "\t 当前持有A锁，尝试获取B锁");
        // 暂停几毫秒线程
        try {
          TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        synchronized (objectB) {
          System.out.println(Thread.currentThread().getName() + "\t 成功获得B锁");
        }
      }
    }, "线程A").start();

    new Thread(() -> {
      synchronized (objectB) {
        System.out.println(Thread.currentThread().getName() + "\t 当前持有B锁，尝试获取A锁");
        // 暂停几毫秒线程
        try {
          TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        synchronized (objectA) {
          System.out.println(Thread.currentThread().getName() + "\t 成功获得A锁");
        }
      }
    }, "线程B").start();
  }
}

/*
#排查死锁:
第1步: >>>> jps -l
10944 com.laioffer.第2部分_高级.第4章_锁.demo5_死锁.Demo51
2835 org.gradle.launcher.daemon.bootstrap.GradleDaemon
10948 jdk.jcmd/sun.tools.jps.Jps
877

第2步: >>>> jstack [进程号]10944
2023-07-19 07:43:19
Full thread dump Java HotSpot(TM) 64-Bit Server VM (11.0.12+8-LTS-237 mixed mode):

Threads class SMR info:
_java_thread_list=0x00006000014ac8c0, length=13, elements={
0x00007f90ad027000, 0x00007f90ac835800, 0x00007f90ab041000, 0x00007f90ad01d800,
0x00007f90ad01e800, 0x00007f90af01a800, 0x00007f90af018800, 0x00007f90ac831000,
0x00007f90ad119000, 0x00007f90ab83e800, 0x00007f90ab83f000, 0x00007f90ad009000,
0x00007f90ad0cf000
}

"Reference Handler" #2 daemon prio=10 os_prio=31 cpu=0.16ms elapsed=48.97s tid=0x00007f90ad027000 nid=0x4203 waiting on condition  [0x000070000bae6000]
   java.lang.Thread.State: RUNNABLE
        at java.lang.ref.Reference.waitForReferencePendingList(java.base@11.0.12/Native Method)
        at java.lang.ref.Reference.processPendingReferences(java.base@11.0.12/Reference.java:241)
        at java.lang.ref.Reference$ReferenceHandler.run(java.base@11.0.12/Reference.java:213)

"Finalizer" #3 daemon prio=8 os_prio=31 cpu=0.25ms elapsed=48.97s tid=0x00007f90ac835800 nid=0x5003 in Object.wait()  [0x000070000bbe9000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(java.base@11.0.12/Native Method)
        - waiting on <0x000000070ff09018> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@11.0.12/ReferenceQueue.java:155)
        - waiting to re-lock in wait() <0x000000070ff09018> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@11.0.12/ReferenceQueue.java:176)
        at java.lang.ref.Finalizer$FinalizerThread.run(java.base@11.0.12/Finalizer.java:170)

"Signal Dispatcher" #4 daemon prio=9 os_prio=31 cpu=0.19ms elapsed=48.95s tid=0x00007f90ab041000 nid=0x5503 runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Service Thread" #5 daemon prio=9 os_prio=31 cpu=0.04ms elapsed=48.95s tid=0x00007f90ad01d800 nid=0x7e03 runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread0" #6 daemon prio=9 os_prio=31 cpu=83.68ms elapsed=48.95s tid=0x00007f90ad01e800 nid=0x7d03 waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE
   No compile task

"C1 CompilerThread0" #14 daemon prio=9 os_prio=31 cpu=72.40ms elapsed=48.95s tid=0x00007f90af01a800 nid=0x5903 waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE
   No compile task

"Sweeper thread" #18 daemon prio=9 os_prio=31 cpu=0.03ms elapsed=48.95s tid=0x00007f90af018800 nid=0x5a03 runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Common-Cleaner" #19 daemon prio=8 os_prio=31 cpu=0.10ms elapsed=48.89s tid=0x00007f90ac831000 nid=0x5c03 in Object.wait()  [0x000070000c301000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
        at java.lang.Object.wait(java.base@11.0.12/Native Method)
        - waiting on <0x000000070fe2a350> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@11.0.12/ReferenceQueue.java:155)
        - waiting to re-lock in wait() <0x000000070fe2a350> (a java.lang.ref.ReferenceQueue$Lock)
        at jdk.internal.ref.CleanerImpl.run(java.base@11.0.12/CleanerImpl.java:148)
        at java.lang.Thread.run(java.base@11.0.12/Thread.java:834)
        at jdk.internal.misc.InnocuousThread.run(java.base@11.0.12/InnocuousThread.java:134)

"Monitor Ctrl-Break" #20 daemon prio=5 os_prio=31 cpu=15.11ms elapsed=48.74s tid=0x00007f90ad119000 nid=0x5f03 runnable  [0x000070000c404000]
   java.lang.Thread.State: RUNNABLE
        at java.net.SocketInputStream.socketRead0(java.base@11.0.12/Native Method)
        at java.net.SocketInputStream.socketRead(java.base@11.0.12/SocketInputStream.java:115)
        at java.net.SocketInputStream.read(java.base@11.0.12/SocketInputStream.java:168)
        at java.net.SocketInputStream.read(java.base@11.0.12/SocketInputStream.java:140)
        at sun.nio.cs.StreamDecoder.readBytes(java.base@11.0.12/StreamDecoder.java:284)
        at sun.nio.cs.StreamDecoder.implRead(java.base@11.0.12/StreamDecoder.java:326)
        at sun.nio.cs.StreamDecoder.read(java.base@11.0.12/StreamDecoder.java:178)
        - locked <0x000000070f904778> (a java.io.InputStreamReader)
        at java.io.InputStreamReader.read(java.base@11.0.12/InputStreamReader.java:181)
        at java.io.BufferedReader.fill(java.base@11.0.12/BufferedReader.java:161)
        at java.io.BufferedReader.readLine(java.base@11.0.12/BufferedReader.java:326)
        - locked <0x000000070f904778> (a java.io.InputStreamReader)
        at java.io.BufferedReader.readLine(java.base@11.0.12/BufferedReader.java:392)
        at com.intellij.rt.execution.application.AppMainV2$1.run(AppMainV2.java:55)

"线程A" #21 prio=5 os_prio=31 cpu=2.16ms elapsed=48.73s tid=0x00007f90ab83e800 nid=0x6103 waiting for monitor entry  [0x000070000c60a000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.laioffer.第2部分_高级.第4章_锁.demo5_死锁.Demo51.lambda$main$0(Demo51.java:26)
        - waiting to lock <0x000000070fba0c60> (a java.lang.Object)
        - locked <0x000000070fba0c50> (a java.lang.Object)
        at com.laioffer.第2部分_高级.第4章_锁.demo5_死锁.Demo51$$Lambda$14/0x0000000800066840.run(Unknown Source)
        at java.lang.Thread.run(java.base@11.0.12/Thread.java:834)

"线程B" #22 prio=5 os_prio=31 cpu=1.63ms elapsed=48.73s tid=0x00007f90ab83f000 nid=0x6203 waiting for monitor entry  [0x000070000c70d000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.laioffer.第2部分_高级.第4章_锁.demo5_死锁.Demo51.lambda$main$1(Demo51.java:41)
        - waiting to lock <0x000000070fba0c50> (a java.lang.Object)
        - locked <0x000000070fba0c60> (a java.lang.Object)
        at com.laioffer.第2部分_高级.第4章_锁.demo5_死锁.Demo51$$Lambda$15/0x0000000800066c40.run(Unknown Source)
        at java.lang.Thread.run(java.base@11.0.12/Thread.java:834)

"DestroyJavaVM" #23 prio=5 os_prio=31 cpu=199.24ms elapsed=48.73s tid=0x00007f90ad009000 nid=0x2903 waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Attach Listener" #24 daemon prio=9 os_prio=31 cpu=0.59ms elapsed=0.11s tid=0x00007f90ad0cf000 nid=0x6303 waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"VM Thread" os_prio=31 cpu=3.73ms elapsed=48.97s tid=0x00007f90ad01f800 nid=0x5403 runnable

"GC Thread#0" os_prio=31 cpu=1.30ms elapsed=49.00s tid=0x00007f90aa812800 nid=0x2f03 runnable

"G1 Main Marker" os_prio=31 cpu=0.37ms elapsed=49.00s tid=0x00007f90aa859000 nid=0x3c03 runnable

"G1 Conc#0" os_prio=31 cpu=0.02ms elapsed=49.00s tid=0x00007f90aa859800 nid=0x3203 runnable

"G1 Refine#0" os_prio=31 cpu=0.37ms elapsed=48.99s tid=0x00007f90ab83b800 nid=0x3a03 runnable

"G1 Young RemSet Sampling" os_prio=31 cpu=7.06ms elapsed=48.99s tid=0x00007f90ab83c000 nid=0x3503 runnable
"VM Periodic Task Thread" os_prio=31 cpu=33.15ms elapsed=48.74s tid=0x00007f90af01c000 nid=0x7703 waiting on condition

JNI global refs: 27, weak refs: 0


Found one Java-level deadlock:
=============================
"线程A":
  waiting to lock monitor 0x00007f90aa313e00 (object 0x000000070fba0c60, a java.lang.Object),
  which is held by "线程B"
"线程B":
  waiting to lock monitor 0x00007f90aa313f00 (object 0x000000070fba0c50, a java.lang.Object),
  which is held by "线程A"

Java stack information for the threads listed above:
===================================================
"线程A":
        at com.laioffer.第2部分_高级.第4章_锁.demo5_死锁.Demo51.lambda$main$0(Demo51.java:26)
        - waiting to lock <0x000000070fba0c60> (a java.lang.Object)  👈🏻👈🏻👈🏻👈🏻
        - locked <0x000000070fba0c50> (a java.lang.Object)           👈🏻👈🏻👈🏻👈🏻
        at com.laioffer.第2部分_高级.第4章_锁.demo5_死锁.Demo51$$Lambda$14/0x0000000800066840.run(Unknown Source)
        at java.lang.Thread.run(java.base@11.0.12/Thread.java:834)
"线程B":
        at com.laioffer.第2部分_高级.第4章_锁.demo5_死锁.Demo51.lambda$main$1(Demo51.java:41)
        - waiting to lock <0x000000070fba0c50> (a java.lang.Object)  👈🏻👈🏻👈🏻👈🏻
        - locked <0x000000070fba0c60> (a java.lang.Object)           👈🏻👈🏻👈🏻👈🏻
        at com.laioffer.第2部分_高级.第4章_锁.demo5_死锁.Demo51$$Lambda$15/0x0000000800066c40.run(Unknown Source)
        at java.lang.Thread.run(java.base@11.0.12/Thread.java:834)

Found 1 deadlock.


 */