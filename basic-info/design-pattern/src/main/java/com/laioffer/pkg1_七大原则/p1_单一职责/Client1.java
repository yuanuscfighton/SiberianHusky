package com.laioffer.pkg1_七大原则.p1_单一职责;

/**
 * 方案1 原始写法
 */
public class Client1 {

  public static void main(String[] args) {
    Vehicle vehicle = new Vehicle();
    vehicle.run("摩托车");
    vehicle.run("汽车");
    vehicle.run("飞机");
  }
}


/**
 * 交通工具
 */
class Vehicle {
  public void run(String vehicle) {
    System.out.println(vehicle + "在公路上运行");
  }
}

/*
   打印结果
      摩托车在公路上运行
      汽车在公路上运行
      飞机在公路上运行

   问题：
      run()方法 既负责陆地上的，又负责水里的，显然不合理 → 违反单一职责原则

   解决方案
      根据交通工具运行方式不同，分解成不同类即可
 */