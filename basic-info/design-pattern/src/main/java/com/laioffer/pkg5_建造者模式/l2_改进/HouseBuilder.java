package com.laioffer.pkg5_建造者模式.l2_改进;


// 抽象的建造者
public abstract class HouseBuilder {

  protected House house = new House();

  // 将建造的流程写好，抽象的方法
  public abstract void buildBasic();

  public abstract void buildWalls();

  public abstract void roofed();

  // 房子盖好后，将产品（房子）返回
  public House buildHouse() {
    return house;
  }

}
