package com.laioffer.pkg1_七大原则.p1_单一职责;

public class Demo2 {

  public static void main(String[] args) {
    RoadVehicle roadVehicle = new RoadVehicle();
    roadVehicle.run("摩托车");
    roadVehicle.run("汽车");

    AirVehicle airVehicle = new AirVehicle();

    airVehicle.run("飞机");
  }
}


/**
 * 方案2的分析
 * (1) 遵守单一职责的原则
 * (2) 但是这样的改动很大，即 将类分解，同时修改客户端
 * (3) 直接修改 Vehicle类，改动的代码会比较少
 */
class RoadVehicle {
  public void run(String vehicle) {
    System.out.println(vehicle + "在公路上");
  }
}


class AirVehicle {
  public void run(String vehicle) {
    System.out.println(vehicle + "在天空");
  }
}


class WaterVehicle {
  public void run(String vehicle) {
    System.out.println(vehicle + "ˮ在水里");
  }
}
