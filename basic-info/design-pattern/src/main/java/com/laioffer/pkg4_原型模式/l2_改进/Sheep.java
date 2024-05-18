package com.laioffer.pkg4_原型模式.l2_改进;


import androidx.annotation.NonNull;

public class Sheep implements Cloneable {
  private final String mName;
  private final int mAge;
  private final String mColor;
  private final String address = "蒙古羊🐑";
  public Sheep friend;

  public Sheep(String name, int age, String color) {
    super();
    mName = name;
    mAge = age;
    mColor = color;
  }

  @NonNull
  @Override
  public String toString() {
    return "Sheep [name=" + mName + ", age=" + mAge + ", color=" + mColor + ", address=" + address + "]";
  }

  // 克隆该实例，使用默认的 clone 方法来完成
  @NonNull
  @Override
  protected Object clone() {

    Sheep sheep = null;
    try {
      sheep = (Sheep) super.clone();
    } catch (Exception e) {
      // 可能会抛出 CloneNotSupportedException 异常
      System.out.println(e.getMessage());
    }
    return sheep;
  }
}
