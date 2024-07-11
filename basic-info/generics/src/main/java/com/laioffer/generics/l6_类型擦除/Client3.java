package com.laioffer.generics.l6_类型擦除;


import java.lang.reflect.Method;

public class Client3 {
  public static void main(String[] args) {
    Class<InfoImpl> infoClass = InfoImpl.class;
    Method[] declaredMethods = infoClass.getDeclaredMethods();
    for (Method m : declaredMethods) {
      System.out.println(m.getName() + " : " + m.getReturnType().getSimpleName());
      // getInfo : Integer
      // getInfo : Object <- 桥接方法，保持架接口和类的实现关系
    }
  }
}


/**
 * 类描述: 接口的类型擦除
 * <p>
 * created by 西伯利亚哈士奇 on 2024/6/1 19:27
 */
interface Info<T> {
  T getInfo(T value);
}


class InfoImpl implements Info<Integer> {

  @Override
  public Integer getInfo(Integer value) {
    return value;
  }
}