package com.laioffer.pkg2_适配器模式.l1_类适配器;

/**
 * 被适配的类
 */
public class Voltage220V {
  // 输出220V的电压
  public int output220V() {
    int src = 220;
    System.out.println("电压: " + src + "V");
    return src;
  }
}
