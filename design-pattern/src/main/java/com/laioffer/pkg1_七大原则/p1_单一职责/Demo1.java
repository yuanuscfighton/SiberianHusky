package com.laioffer.pkg1_七大原则.p1_单一职责;

public class Demo1 {

  public static void main(String[] args) {
    Vehicle vehicle = new Vehicle();
    vehicle.run("摩托车");
    vehicle.run("汽车");
    vehicle.run("飞机");
  }
}


/**
 * 交通工具
 * 方式1
 * (1) 在方式1的run()方法中，违反了单一职责的原则
 * (2) 解决的方案: 根据交通工具运行方式不同，分解成不同类即可
 */
class Vehicle {
  public void run(String vehicle) {
    System.out.println(vehicle + "在公路上运行");
  }
}
