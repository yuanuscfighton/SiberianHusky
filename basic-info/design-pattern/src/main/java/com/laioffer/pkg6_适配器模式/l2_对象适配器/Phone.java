package com.laioffer.pkg6_适配器模式.l2_对象适配器;

public class Phone {

  public void charging(IVoltage5V iVoltage5V) {
    if (iVoltage5V.output5V() == 5) {
      System.out.println("电压是5V，可以充电了...");
    } else if (iVoltage5V.output5V() > 5) {
      System.out.println("电压超过5V, 不能充电...");
    }
  }
}
