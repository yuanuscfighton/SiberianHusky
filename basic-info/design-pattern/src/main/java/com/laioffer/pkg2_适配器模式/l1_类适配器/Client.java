package com.laioffer.pkg2_适配器模式.l1_类适配器;

/**
 * 类描述: 适配器模式. 类适配器
 * <p>
 * created by 春夏秋冬在中南 on 2024/4/30 08:33
 */
public class Client {

  public static void main(String[] args) {
    System.out.println("========== 类适配器模式 ==========");
    Phone phone = new Phone();
    phone.charging(new VoltageAdapter());
  }

}

/*
	类图：https://www.processon.com/diagraming/66303a7193cfc118d4c127b7

  类适配器基本介绍：
      adapter 类，通过继承 src 类，实现 dst 类接口，完成 src → dst 的适配

	类适配器模式注意事项和细节
	  （1）Java 是单继承模式，所以类适配器需要继承 src 类(即，Voltage220V) ← 这一点是一个缺点。因为这要求 dst 必须是接口(即，IVoltage5V)，有一定局限性
	  （2）src 类的方法在 adapter 中都会暴漏出来，也增加了使用的成本。即，Voltage22V # output220V() 方法
	  （3）由于其继承了 src 类，所以它可以根据需求重写 src 类的方法，使得 adapter 的灵活性增强了
 */
