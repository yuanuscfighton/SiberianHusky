package com.laioffer.第2部分_高级.第3章_锁.demo2_从字节码角度分析synchronized;

/**
 * 类的描述: 从字节码角度分析「同步代码块」
 * Created by 春夏秋冬在中南 on 2023/7/13 23:00
 */
public class Demo321 {
  Object o = new Object();

  public void foo() {
    synchronized (o) {
      System.out.println("同步代码块");

      throw new RuntimeException("验证是否是1个monitorenter和2个monitorexit");
    }
  }

  public static void main(String[] args) {
  }
}


/*
 生成的Class文件 在 "build/intermediates/javac/debug/classes/com/laioffer/第2部分_高级/第3章_锁/demo2_从字节码角度分析synchronized" 目录中

1. 反编译: javap -c Demo21.class

  public void foo();
    Code:
       0: aload_0
       1: getfield      #7                  // Field o:Ljava/lang/Object;
       4: dup
       5: astore_1
       6: monitorenter          👈🏻 获得锁🔐
       7: getstatic     #13                 // Field java/lang/System.out:Ljava/io/PrintStream;
      10: ldc           #19                 // String 同步代码块
      12: invokevirtual #21                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      15: aload_1
      16: monitorexit           👈🏻 释放锁🔐 ← 程序运行正常情况下，正常退出锁
      17: goto          25
      20: astore_2
      21: aload_1
      22: monitorexit           👈🏻 此处为什么会多出一个monitorexit ← 程序异常情况下，保证也能退出锁的持有
      23: aload_2
      24: athrow
      25: return


2. 小结
  「同步代码块」实现原理: 使用的是monitorenter和monitorexit指令

3. 一定是一个enter和两个exit吗？
     i. 一般是1个monitorenter和2个monitorexit指令
     ii. 特殊情况，如 throw new RuntimeException("xxxx");
        再执行 javap -c Demo321.class

    public void foo();
      Code:
         0: aload_0
         1: getfield      #7                  // Field o:Ljava/lang/Object;
         4: dup
         5: astore_1
         6: monitorenter        👈🏻 获得锁🔐
         7: getstatic     #13                 // Field java/lang/System.out:Ljava/io/PrintStream;
        10: ldc           #19                 // String 同步代码块
        12: invokevirtual #21                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        15: new           #27                 // class java/lang/RuntimeException
        18: dup
        19: ldc           #29                 // String 验证是否是1个monitorenter和2个monitorexit
        21: invokespecial #31                 // Method java/lang/RuntimeException."<init>":(Ljava/lang/String;)V
        24: athrow
        25: astore_2
        26: aload_1
        27: monitorexit         👈🏻 异常退出，释放锁🔐
        28: aload_2
        29: athrow



*/
