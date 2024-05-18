package com.laioffer.pkg5_建造者模式.l2_改进;

public class Client {
  public static void main(String[] args) {

    CommonHouse commonHouse = new CommonHouse();
    HouseDirector houseDirector = new HouseDirector(commonHouse);

    House house = houseDirector.constructHouse();

    System.out.println("--------------------------");
    HighHouse highBuilding = new HighHouse();
    houseDirector.setHouseBuilder(highBuilding);
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
