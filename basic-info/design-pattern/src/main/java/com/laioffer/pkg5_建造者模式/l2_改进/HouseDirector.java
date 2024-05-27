package com.laioffer.pkg5_建造者模式.l2_改进;

// 指挥者，这里去指定制作流程，返回产品
public class HouseDirector {

  HouseBuilder mHouseBuilder;

  // 方式1：构造器传入 houseBuilder
  public HouseDirector(HouseBuilder houseBuilder) {
    mHouseBuilder = houseBuilder;
  }

  // 方式2：Setter 方法传入 houseBuilder
  public void setHouseBuilder(HouseBuilder houseBuilder) {
    mHouseBuilder = houseBuilder;
  }

  // 如何处理建造房子的流程？交给指挥者
  public House constructHouse() {
    mHouseBuilder.buildBasic();
    mHouseBuilder.buildWalls();
    mHouseBuilder.roofed();
    return mHouseBuilder.buildHouse();
  }
}
