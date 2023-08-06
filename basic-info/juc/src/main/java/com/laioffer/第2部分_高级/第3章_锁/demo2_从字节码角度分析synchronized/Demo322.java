package com.laioffer.第2部分_高级.第3章_锁.demo2_从字节码角度分析synchronized;

/**
 * 类的描述: 从字节码角度分析「普通同步方法」
 * Created by 春夏秋冬在中南 on 2023/7/15 16:21
 */
public class Demo322 {

  public synchronized void foo() {
    System.out.println("同步方法");
  }

  public static void main(String[] args) {
  }
}

/*
编译后的Class文件路径: build/intermediates/javac/debug/classes/com/laioffer/第2部分_高级/第3章_锁/demo2_从字节码角度分析synchronized

1.javap -v Demo322.class: -v verbose 输出附加信息(包括行号、本地变量表、反汇编等详细信息)

  public synchronized void foo();
    descriptor: ()V
    flags: (0x0021) ACC_PUBLIC, ACC_SYNCHRONIZED 👈🏻 ACC_SYNCHRONIZED标识 同步方法
    Code:
      stack=2, locals=1, args_size=1
         0: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
         3: ldc           #13                 // String 同步方法
         5: invokevirtual #15                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         8: return
      LineNumberTable:
        line 10: 0
        line 11: 8
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       9     0  this   Lcom/laioffer/第2部分_高级/第3章_锁/demo2_从字节码角度分析synchronized/Demo322;


2.小结:
  「普通同步方法」实现原理:
      调用指令将会检查方法的 ACC_SYNCHRONIZED 访问标志是否被设置。
      如果设置了，执行线程会将先持有monitor锁，然后再执行方法，最后在方法完成(无论是正常完成还是非正常完成)时释放monitor
 */
