package com.laioffer.pkg2_适配器模式.l2_对象适配器;

import androidx.annotation.NonNull;

public class VoltageAdapter implements IVoltage5V {

  @NonNull
  private final Voltage220V mVoltage220V; // 关联关系中的聚合关系

  public VoltageAdapter(@NonNull Voltage220V voltage220v) {
    mVoltage220V = voltage220v;
  }


  @Override
  public int output5V() {
    int dst;
    // 获取 220V 电压
    int src = mVoltage220V.output220V();
    System.out.println("使用对象适配器，进行适配...");
    dst = src / 44;
    System.out.println("适配完成，输出的电压是 " + dst + "V");

    return dst;
  }

}
