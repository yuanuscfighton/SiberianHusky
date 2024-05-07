package com.laioffer.pkg3_桥接模式;

public class Client {

  public static void main(String[] args) {

    // 获取折叠屏样式的手机（样式 + 品牌）
    Phone xiaomiFoldedPhone = new FoldedPhone(new XiaoMi());

    xiaomiFoldedPhone.open();
    xiaomiFoldedPhone.call();
    xiaomiFoldedPhone.close();

    System.out.println("====================");

    // 折叠屏样式的Vivo手机
    Phone vivoFoldedPhone = new FoldedPhone(new Vivo());

    vivoFoldedPhone.open();
    vivoFoldedPhone.call();
    vivoFoldedPhone.close();

    System.out.println("====================");

    UpRightPhone xiaomiUpRightPhone = new UpRightPhone(new XiaoMi());

    xiaomiUpRightPhone.open();
    xiaomiUpRightPhone.call();
    xiaomiUpRightPhone.close();

    System.out.println("====================");

    UpRightPhone vivoUpRightPhone = new UpRightPhone(new Vivo());

    vivoUpRightPhone.open();
    vivoUpRightPhone.call();
    vivoUpRightPhone.close();
  }
}


/*
  桥接模式介绍
  （1）桥接模式是指：将实现与抽象放在两个不同的层次中，使两个层次可以独立改变
  （2）是一种结构型设计模式
  （3）Bridge 模式基于类的最小设计原则，通过使用封装、聚合及继承等行为，让不同的类承担不同的职责。
      它的主要特点是把抽象与行为实现分离开来，从而可以保持各个部分的独立性以及对应它们的功能扩展



 */