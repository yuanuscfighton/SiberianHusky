package com.laioffer.第2部分_高级.第4章_锁.demo2_从字节码角度分析synchronized;

/**
 * 类的描述: 从字节码角度分析「同步代码块」
 * Created by 春夏秋冬在中南 on 2023/7/13 23:00
 */
public class Demo21 {
  Object o = new Object();

  public void m1() {
    synchronized (o) {
      System.out.println("[demo21] 同步代码块");
    }
  }

  public static void main(String[] args) {
  }
}

/*
  1.反编译: javap -c Demo21.class
       6: monitorenter // 锁的获取
       7: getstatic     #13            // Field java/lang/System.out:Ljava/io/PrintStream;
      10: ldc           #19            // String [demo21] 同步代码块
      12: invokevirtual #21            // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      15: aload_1
      16: monitorexit  // 锁的释放
      17: goto          25
      20: astore_2
      21: aload_1
      22: monitorexit


2.小结
  (1) 「同步代码块」实现原理: 使用的是monitorenter和monitorexit指令
  (2) 一般是1个monitorenter和2个monitorexit指令

*/
