package com.laioffer.pkg6_适配器模式.l2_对象适配器;

/**
 * 被适配的类
 */
public class Voltage220V {
  // 输出 220V 电压
  public int output220V() {
    int src = 220;
    System.out.println("电压: " + src + "V");
    return src;
  }
}
