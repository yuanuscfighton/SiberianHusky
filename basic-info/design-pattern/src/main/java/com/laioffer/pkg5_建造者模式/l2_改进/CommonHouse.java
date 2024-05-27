package com.laioffer.pkg5_建造者模式.l2_改进;

public class CommonHouse extends HouseBuilder {

  @Override
  public void buildBasic() {
    System.out.println("普通房子打地基5米");
  }

  @Override
  public void buildWalls() {
    System.out.println("普通房子砌墙10米");
  }

  @Override
  public void roofed() {
    System.out.println("普通房子封顶");
  }

  // 房子具体的建造过程是写在子类里了（即，CommonHouse），产品本身属性是封装在 House 里了
  // 将产品和制造流程分开了（解耦了）
}
