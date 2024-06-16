package com.laioffer.serializable;

import java.io.Serializable;

/**
 * serialVersionUID的理解
 */
public class demo01 {



  static class User1 implements Serializable {

    private static final long serialVersionUID = 2;

    public User1(String name, int age) {
      this.name = name;
      this.age = age;
    }

    public String name;
    public int age;
    public String nickName;

    @Override
    public String toString() {
      return "User1{" +
          "name='" + name + '\'' +
          ", age=" + age +
          ", nickName=" + nickName +
          '}';
    }
  }

  public static String basePath = System.getProperty("user.dir") + "\\";
  public static String tmp = "D:\\xuliehuaTest\\";

  public static void main(String[] args) {
//        System.out.println("BasePath= " + basePath);
//        NoSerialIdTest();
    HasSerialIdTest();
  }



  private static void HasSerialIdTest() {
    User1 user = new User1("zero", 18);
//        SerializeableUtils.saveObject(user,tmp+"b.out");
//        System.out.println("1: " + user);
    user = SerializeableUtils.readObject(tmp + "b.out");
    System.out.println("2: " + user);
  }
}