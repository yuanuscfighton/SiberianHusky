package com.laioffer.generics.l6_类型擦除;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 类描述:
 * <p>
 * created by 西伯利亚哈士奇 on 2024/6/1 19:12
 */
public class Client2 {
  public static void main(String[] args) {
    Erase2<Integer> erase = new Erase2<>();
    Class<? extends Erase2> clz = erase.getClass();
    // 获取所有的成员变量
    Method[] declaredMethods = clz.getDeclaredMethods();
    for (Method m : declaredMethods) {
      System.out.println(m.getName() + " : " + m.getReturnType());
      // setKey : void
      // show : interface java.util.List
      // getKey : class java.lang.Number
    }
  }
}

/**
 * 类描述: 擦除方法中类型定义的参数
 * <p>
 * created by 西伯利亚哈士奇 on 2024/6/1 19:04
 */
class Erase2<T extends Number> {

  private T key;

  public T getKey() {
    return key;
  }

  public void setKey(T key) {
    this.key = key;
  }

  public <T extends List> T show(T t) {
    return t;
  }
}
