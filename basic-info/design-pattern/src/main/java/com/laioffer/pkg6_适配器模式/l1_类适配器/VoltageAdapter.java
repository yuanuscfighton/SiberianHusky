package com.laioffer.pkg6_适配器模式.l1_类适配器;

/**
 * 类适配器
 */
public class VoltageAdapter extends Voltage220V implements IVoltage5V {

  @Override
  public int output5V() {
    // 第1步：获取 220V 电压
    int srcV = output220V();
    // 第2步：将 220V 转成 5V
    return srcV / 44;
  }

}
