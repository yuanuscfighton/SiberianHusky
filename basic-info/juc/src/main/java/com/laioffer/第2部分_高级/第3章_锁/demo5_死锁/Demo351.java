package com.laioffer.第2部分_高级.第3章_锁.demo5_死锁;

import java.util.concurrent.TimeUnit;

/**
 * 类的描述: 死锁案例
 * Created by 春夏秋冬在中南 on 2023/7/19 07:37
 */
public class Demo351 {


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
2599
3853 jdk.jcmd/sun.tools.jps.Jps
3822 com.laioffer.第2部分_高级.第3章_锁.demo5_死锁.Demo351
2687 org.gradle.launcher.daemon.bootstrap.GradleDaemon


第2步: >>>> jstack [进程号]3822
2023-08-07 08:50:03
Full thread dump Java HotSpot(TM) 64-Bit Server VM (11.0.12+8-LTS-237 mixed mode):

Threads class SMR info:
_java_thread_list=0x000060000177eba0, length=13, elements={
0x00007ff281033000, 0x00007ff28300d000, 0x00007ff282817000, 0x00007ff286808800,
0x00007ff27f906800, 0x00007ff27f907800, 0x00007ff282818000, 0x00007ff286011000,
0x00007ff282828000, 0x00007ff28205c000, 0x00007ff282061000, 0x00007ff28604d000,
0x00007ff282017000
}

"Reference Handler" #2 daemon prio=10 os_prio=31 cpu=0.19ms elapsed=55.41s tid=0x00007ff281033000 nid=0x5203 waiting on condition  [0x0000700009ea9000]
   java.lang.Thread.State: RUNNABLE
        at java.lang.ref.Reference.waitForReferencePendingList(java.base@11.0.12/Native Method)
        at java.lang.ref.Reference.processPendingReferences(java.base@11.0.12/Reference.java:241)
        at java.lang.ref.Reference$ReferenceHandler.run(java.base@11.0.12/Reference.java:213)

"Finalizer" #3 daemon prio=8 os_prio=31 cpu=0.38ms elapsed=55.41s tid=0x00007ff28300d000 nid=0x4203 in Object.wait()  [0x0000700009fac000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(java.base@11.0.12/Native Method)
        - waiting on <0x000000070ff09018> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@11.0.12/ReferenceQueue.java:155)
        - waiting to re-lock in wait() <0x000000070ff09018> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@11.0.12/ReferenceQueue.java:176)
        at java.lang.ref.Finalizer$FinalizerThread.run(java.base@11.0.12/Finalizer.java:170)

"Signal Dispatcher" #4 daemon prio=9 os_prio=31 cpu=0.28ms elapsed=55.39s tid=0x00007ff282817000 nid=0x5503 runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Service Thread" #5 daemon prio=9 os_prio=31 cpu=0.06ms elapsed=55.39s tid=0x00007ff286808800 nid=0x7d03 runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread0" #6 daemon prio=9 os_prio=31 cpu=96.27ms elapsed=55.39s tid=0x00007ff27f906800 nid=0x7b03 waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE
   No compile task

"C1 CompilerThread0" #14 daemon prio=9 os_prio=31 cpu=57.07ms elapsed=55.39s tid=0x00007ff27f907800 nid=0x5803 waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE
   No compile task

"Sweeper thread" #18 daemon prio=9 os_prio=31 cpu=0.07ms elapsed=55.39s tid=0x00007ff282818000 nid=0x7703 runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Common-Cleaner" #19 daemon prio=8 os_prio=31 cpu=0.13ms elapsed=55.31s tid=0x00007ff286011000 nid=0x7303 in Object.wait()  [0x000070000a7c7000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
        at java.lang.Object.wait(java.base@11.0.12/Native Method)
        - waiting on <0x000000070fe2ee58> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@11.0.12/ReferenceQueue.java:155)
        - waiting to re-lock in wait() <0x000000070fe2ee58> (a java.lang.ref.ReferenceQueue$Lock)
        at jdk.internal.ref.CleanerImpl.run(java.base@11.0.12/CleanerImpl.java:148)
        at java.lang.Thread.run(java.base@11.0.12/Thread.java:834)
        at jdk.internal.misc.InnocuousThread.run(java.base@11.0.12/InnocuousThread.java:134)

"Monitor Ctrl-Break" #20 daemon prio=5 os_prio=31 cpu=17.82ms elapsed=55.16s tid=0x00007ff282828000 nid=0x5b03 runnable  [0x000070000a8ca000]
   java.lang.Thread.State: RUNNABLE
        at java.net.SocketInputStream.socketRead0(java.base@11.0.12/Native Method)
        at java.net.SocketInputStream.socketRead(java.base@11.0.12/SocketInputStream.java:115)
        at java.net.SocketInputStream.read(java.base@11.0.12/SocketInputStream.java:168)
        at java.net.SocketInputStream.read(java.base@11.0.12/SocketInputStream.java:140)
        at sun.nio.cs.StreamDecoder.readBytes(java.base@11.0.12/StreamDecoder.java:284)
        at sun.nio.cs.StreamDecoder.implRead(java.base@11.0.12/StreamDecoder.java:326)
        at sun.nio.cs.StreamDecoder.read(java.base@11.0.12/StreamDecoder.java:178)
        - locked <0x000000070fa8eb98> (a java.io.InputStreamReader)
        at java.io.InputStreamReader.read(java.base@11.0.12/InputStreamReader.java:181)
        at java.io.BufferedReader.fill(java.base@11.0.12/BufferedReader.java:161)
        at java.io.BufferedReader.readLine(java.base@11.0.12/BufferedReader.java:326)
        - locked <0x000000070fa8eb98> (a java.io.InputStreamReader)
        at java.io.BufferedReader.readLine(java.base@11.0.12/BufferedReader.java:392)
        at com.intellij.rt.execution.application.AppMainV2$1.run(AppMainV2.java:56)

"线程A" #21 prio=5 os_prio=31 cpu=1.50ms elapsed=55.15s tid=0x00007ff28205c000 nid=0x5f03 waiting for monitor entry  [0x000070000aad0000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.laioffer.第2部分_高级.第3章_锁.demo5_死锁.Demo351.lambda$main$0(Demo351.java:26)
        - waiting to lock <0x000000070fa0e520> (a java.lang.Object)
        - locked <0x000000070fa0e510> (a java.lang.Object)
        at com.laioffer.第2部分_高级.第3章_锁.demo5_死锁.Demo351$$Lambda$14/0x0000000800066840.run(Unknown Source)
        at java.lang.Thread.run(java.base@11.0.12/Thread.java:834)

"线程B" #22 prio=5 os_prio=31 cpu=1.29ms elapsed=55.15s tid=0x00007ff282061000 nid=0x6f03 waiting for monitor entry  [0x000070000abd3000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.laioffer.第2部分_高级.第3章_锁.demo5_死锁.Demo351.lambda$main$1(Demo351.java:41)
        - waiting to lock <0x000000070fa0e510> (a java.lang.Object)
        - locked <0x000000070fa0e520> (a java.lang.Object)
        at com.laioffer.第2部分_高级.第3章_锁.demo5_死锁.Demo351$$Lambda$15/0x0000000800066c40.run(Unknown Source)
        at java.lang.Thread.run(java.base@11.0.12/Thread.java:834)

"DestroyJavaVM" #23 prio=5 os_prio=31 cpu=231.79ms elapsed=55.15s tid=0x00007ff28604d000 nid=0x2203 waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Attach Listener" #24 daemon prio=9 os_prio=31 cpu=0.76ms elapsed=0.10s tid=0x00007ff282017000 nid=0x5a07 waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"VM Thread" os_prio=31 cpu=3.64ms elapsed=55.41s tid=0x00007ff28600a800 nid=0x5403 runnable

"GC Thread#0" os_prio=31 cpu=1.36ms elapsed=55.44s tid=0x00007ff27f80c000 nid=0x2e03 runnable

"G1 Main Marker" os_prio=31 cpu=0.33ms elapsed=55.44s tid=0x00007ff27f841800 nid=0x3c03 runnable

"G1 Conc#0" os_prio=31 cpu=0.02ms elapsed=55.44s tid=0x00007ff27f842800 nid=0x3903 runnable

"G1 Refine#0" os_prio=31 cpu=0.31ms elapsed=55.44s tid=0x00007ff27f8f7000 nid=0x3803 runnable

"G1 Young RemSet Sampling" os_prio=31 cpu=5.81ms elapsed=55.44s tid=0x00007ff282819000 nid=0x3703 runnable
"VM Periodic Task Thread" os_prio=31 cpu=28.24ms elapsed=55.15s tid=0x00007ff27f97b800 nid=0x5d03 waiting on condition

JNI global refs: 27, weak refs: 0


Found one Java-level deadlock:
=============================
"线程A":
  waiting to lock monitor 0x00007ff280413e00 (object 0x000000070fa0e520, a java.lang.Object),
  which is held by "线程B"
"线程B":
  waiting to lock monitor 0x00007ff280413f00 (object 0x000000070fa0e510, a java.lang.Object),
  which is held by "线程A"

Java stack information for the threads listed above:
===================================================
"线程A":
        at com.laioffer.第2部分_高级.第3章_锁.demo5_死锁.Demo351.lambda$main$0(Demo351.java:26)
        - waiting to lock <0x000000070fa0e520> (a java.lang.Object)
        - locked <0x000000070fa0e510> (a java.lang.Object)
        at com.laioffer.第2部分_高级.第3章_锁.demo5_死锁.Demo351$$Lambda$14/0x0000000800066840.run(Unknown Source)
        at java.lang.Thread.run(java.base@11.0.12/Thread.java:834)
"线程B":
        at com.laioffer.第2部分_高级.第3章_锁.demo5_死锁.Demo351.lambda$main$1(Demo351.java:41)
        - waiting to lock <0x000000070fa0e510> (a java.lang.Object)
        - locked <0x000000070fa0e520> (a java.lang.Object)
        at com.laioffer.第2部分_高级.第3章_锁.demo5_死锁.Demo351$$Lambda$15/0x0000000800066c40.run(Unknown Source)
        at java.lang.Thread.run(java.base@11.0.12/Thread.java:834)

Found 1 deadlock.


 */