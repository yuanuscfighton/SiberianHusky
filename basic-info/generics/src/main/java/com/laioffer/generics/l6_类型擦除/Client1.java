package com.laioffer.generics.l6_类型擦除;

import java.lang.reflect.Field;

/**
 * 类描述:
 * <p>
 * created by 西伯利亚哈士奇 on 2024/6/1 19:12
 */
public class Client1 {
  public static void main(String[] args) {
    Erase1<Integer> erase = new Erase1<>();
    Class<? extends Erase1> clz = erase.getClass();
    // 获取所有的成员变量
    Field[] declaredFields = clz.getDeclaredFields();
    for (Field f : declaredFields) {
      System.out.println(f.getName() + " : " + f.getType().getSimpleName());
      // key : Number <- 有限制类型擦除
    }
  }
}

/**
 * 类描述: 有限制类型擦除
 * <p>
 * created by 西伯利亚哈士奇 on 2024/6/1 19:04
 */
class Erase1<T extends Number> {

  private T key;

  public T getKey() {
    return key;
  }

  public void setKey(T key) {
    this.key = key;
  }
}
