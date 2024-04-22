package com.laioffer.pkg1_七大原则.p1_单一职责;

/**
 * 类描述: 方案2，拆分成多个类
 * <p>
 * created by 春夏秋冬在中南 on 2024/4/14 16:39
 */
public class Client2 {

  public static void main(String[] args) {
    RoadVehicle roadVehicle = new RoadVehicle();
    roadVehicle.run("摩托车");
    roadVehicle.run("汽车");

    AirVehicle airVehicle = new AirVehicle();

    airVehicle.run("飞机");
  }
}

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

/*
  分析方案2
  （1）遵守单一职责的原则
  （2）但是这样的改动很大。即，将类分解(i.e. RoadVehicle、AirVehicle、WaterVehicle)，同时修改客户端代码(i.e. Client2 类)

  改进方案
      直接修改 Vehicle 类，改动的代码会比较少
 */
