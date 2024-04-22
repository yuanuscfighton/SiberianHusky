package com.laioffer.pkg1_七大原则.p1_单一职责;

/**
 * 类描述: 只修改 Vehicle 类
 * <p>
 * created by 春夏秋冬在中南 on 2024/4/14 16:44
 */
public class Demo3 {

  public static void main(String[] args) {

    Vehicle2 vehicle2 = new Vehicle2();
    vehicle2.run("汽车");
    vehicle2.runWater("轮船");
    vehicle2.runAir("飞机");
  }
}

class Vehicle2 {
  public void run(String vehicle) {
    System.out.println(vehicle + " 在公路上运行....");
  }

  public void runAir(String vehicle) {
    System.out.println(vehicle + " 在天上飞的....");
  }

  public void runWater(String vehicle) {
    System.out.println(vehicle + " 在水里跑的....");
  }
}

/*
  方式3分析
  （1）这种修改方法没有对原来的类做大的修改，只是增加方法
  （2）这里虽然没有在“类”这个级别上遵守单一职责原则，但是在“方法”级别上，仍然是遵守单一职责。

  总结
    单一职责就是各行其职
 */
