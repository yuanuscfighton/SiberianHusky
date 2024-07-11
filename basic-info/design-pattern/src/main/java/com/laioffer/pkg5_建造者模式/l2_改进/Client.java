package com.laioffer.pkg5_建造者模式.l2_改进;

public class Client {
  public static void main(String[] args) {

    HouseBuilder commonHouseBuilder = new CommonHouse();
    HouseDirector houseDirector = new HouseDirector(commonHouseBuilder);
    House house = houseDirector.constructHouse();
    System.out.println(house.getWall());

    System.out.println("--------------------------");
    HouseBuilder highHouseBuilder = new HighHouse();
    houseDirector.setHouseBuilder(highHouseBuilder);
    houseDirector.constructHouse();
  }

  /*
      普通房子打地基5米
      普通房子砌墙10米
      普通房子封顶
      --------------------------
      高楼打地基100米
      高楼砌墙20米
      高楼封顶
   */
}
