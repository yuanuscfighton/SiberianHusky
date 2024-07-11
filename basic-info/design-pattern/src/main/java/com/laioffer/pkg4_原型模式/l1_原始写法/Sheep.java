package com.laioffer.pkg4_原型模式.l1_原始写法;

import androidx.annotation.NonNull;

public class Sheep {
  private String mName;
  private int mAge;
  private String mColor;

  public Sheep(String name, int age, String color) {
    super();
    mName = name;
    mAge = age;
    mColor = color;
  }

  public String getName() {
    return mName;
  }

  public void setName(String name) {
    mName = name;
  }

  public int getAge() {
    return mAge;
  }

  public void setAge(int age) {
    mAge = age;
  }

  public String getColor() {
    return mColor;
  }

  public void setColor(String color) {
    this.mColor = color;
  }

  @NonNull
  @Override
  public String toString() {
    return "Sheep [name=" + mName + ", age=" + mAge + ", color=" + mColor + "]";
  }

}
