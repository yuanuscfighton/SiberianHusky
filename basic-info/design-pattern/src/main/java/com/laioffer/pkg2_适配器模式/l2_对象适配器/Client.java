package com.laioffer.pkg2_适配器模式.l2_对象适配器;

public class Client {

  public static void main(String[] args) {
    System.out.println("========== 对象适配器 ==========");
    Phone phone = new Phone();
    phone.charging(new VoltageAdapter(new Voltage220V()));
  }

}

/*
	对象适配器模式介绍
	（1）基本思路和类适配器模式相同，只是将 adapter 类进行修改，不是继承 src 类，而是持有 src 类的实例，来解决兼容性的问题。即，持有 src 类，实现 dst 类接口，完成 src → dst 的适配
	（2）根据 "合成复用原则"，在系统中尽量使用「关联关系」来替代「继承关系」
	（3）对象适配器模式是适配器模式常用的一种
 */
