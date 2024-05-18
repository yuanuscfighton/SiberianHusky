package com.laioffer.pkg5_建造者模式.l2_改进;

public class HouseDirector {

  HouseBuilder mHouseBuilder;

  // 构造器传入 houseBuilder
  public HouseDirector(HouseBuilder houseBuilder) {
    mHouseBuilder = houseBuilder;
  }

  // Setter 方法传入 houseBuilder
  public void setHouseBuilder(HouseBuilder houseBuilder) {
    mHouseBuilder = houseBuilder;
  }

  public House constructHouse() {
    mHouseBuilder.buildBasic();
    mHouseBuilder.buildWalls();
    mHouseBuilder.roofed();
    return mHouseBuilder.buildHouse();
  }
}
