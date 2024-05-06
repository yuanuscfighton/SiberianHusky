package com.laioffer.pkg2_适配器模式.l1_类适配器;

/**
 * 类适配器
 */
public class VoltageAdapter extends Voltage220V implements IVoltage5V {

  @Override
  public int output5V() {
    // 获取 220V 电压
    int srcV = output220V();
    // 将 220V 转成 5V
    int dstV = srcV / 44;
    return dstV;
  }

}
