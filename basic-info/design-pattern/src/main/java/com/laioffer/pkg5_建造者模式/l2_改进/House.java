package com.laioffer.pkg5_建造者模式.l2_改进;

// 产品 → Product
public class House {
  private String mBasic;
  private String mWall;
  private String mRoofed;

  public String getBasic() {
    return mBasic;
  }

  public void setBasic(String basic) {
    mBasic = basic;
  }

  public String getWall() {
    return mWall;
  }

  public void setWall(String wall) {
    mWall = wall;
  }

  public String getRoofed() {
    return mRoofed;
  }

  public void setRoofed(String roofed) {
    mRoofed = roofed;
  }

}
