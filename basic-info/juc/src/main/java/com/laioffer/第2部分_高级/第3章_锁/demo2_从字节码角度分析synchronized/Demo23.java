package com.laioffer.第2部分_高级.第3章_锁.demo2_从字节码角度分析synchronized;

/**
 * 类的描述: 从字节码角度分析「静态同步方法」
 * Created by 春夏秋冬在中南 on 2023/7/15 16:33
 */
public class Demo23 {

  public static synchronized void m3() {
    System.out.println("[demo23] 静态同步方法");
  }

  public static void main(String[] args) {
  }
}

/*
1.javap -v Demo23.class: -v verbose 输出附加信息(包括行号、本地变量表、反汇编等详细信息)
  public static synchronized void m3();
    descriptor: ()V
    flags: (0x0029) ACC_PUBLIC, ACC_STATIC, ACC_SYNCHRONIZED 👈🏻 "ACC_STATIC" 和 "ACC_SYNCHRONIZED"访问标志区分是否是静态同步方法
    Code:
      stack=2, locals=0, args_size=0
         0: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
         3: ldc           #13                 // String [demo23] 静态同步方法
         5: invokevirtual #15                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         8: return
      LineNumberTable:
        line 10: 0
        line 11: 8

 */
