package com.laioffer.pkg8_工厂模式.l2_工厂方法.order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PizzaStore {
  public static void main(String[] args) {
    String loc = getLocation();
    if (loc.equals("bj")) {
      new BJOrderPizza();
    } else {
      new LDOrderPizza();
    }
  }

  private static String getLocation() {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("输入地区: ");
      return in.readLine();
    } catch (IOException e) {
      e.printStackTrace();
      return "";
    }
  }

}
