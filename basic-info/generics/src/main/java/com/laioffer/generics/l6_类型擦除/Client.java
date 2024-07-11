package com.laioffer.generics.l6_类型擦除;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// 类型擦除
public class Client {
  public static void main(String[] args) {
    List<Integer> intList = new ArrayList<>();
    List<String> strList = new ArrayList<>();

    System.out.println(intList.getClass().getSimpleName());
    System.out.println(strList.getClass().getSimpleName());

    System.out.println(intList.getClass() == strList.getClass());

    System.out.println("====================");

    Erase<Integer> erase = new Erase<>();
    Class<? extends Erase> clz = erase.getClass();
    // 获取所有的成员变量
    Field[] declaredFields = clz.getDeclaredFields();
    for (Field f : declaredFields) {
      System.out.println(f.getName() + " : " + f.getType().getSimpleName());
      // key : Object <- 无限制类型擦除
    }
  }
}

/**
 * 类描述: 无限制类型擦除
 * <p>
 * created by 西伯利亚哈士奇 on 2024/6/1 19:04
 */
class Erase<T> {

  private T key;

  public T getKey() {
    return key;
  }

  public void setKey(T key) {
    this.key = key;
  }
}