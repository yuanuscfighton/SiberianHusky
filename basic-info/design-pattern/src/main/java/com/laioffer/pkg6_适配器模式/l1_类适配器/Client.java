package com.laioffer.pkg6_适配器模式.l1_类适配器;

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


 */
