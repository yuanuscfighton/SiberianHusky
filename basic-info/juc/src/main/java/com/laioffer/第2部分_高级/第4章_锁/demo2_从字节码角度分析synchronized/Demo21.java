package com.laioffer.第2部分_高级.第4章_锁.demo2_从字节码角度分析synchronized;

/**
 * 类的描述: 从字节码角度分析同步代码块
 * Created by 春夏秋冬在中南 on 2023/7/13 23:00
 */
public class Demo21 {
  Object o = new Object();

  public void m1() {
    synchronized (o) {
      System.out.println("[demo] 同步代码块");
    }
  }

  public static void main(String[] args) {
  }
}
