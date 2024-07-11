package com.laioffer.pkg4_原型模式.l1_原始写法;

public class Client {

  public static void main(String[] args) {

    // 传统的写法，复制一模一样的羊🐑
    Sheep sheep = new Sheep("tom", 1, "��ɫ");

    Sheep sheep2 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
    Sheep sheep3 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
    Sheep sheep4 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
    Sheep sheep5 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
    //....

    System.out.println(sheep);
    System.out.println(sheep2);
    System.out.println(sheep3);
    System.out.println(sheep4);
    System.out.println(sheep5);
    //...
  }

}
