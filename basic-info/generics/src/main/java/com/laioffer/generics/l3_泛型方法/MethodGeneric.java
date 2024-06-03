package com.laioffer.generics.l3_泛型方法;

import java.util.ArrayList;
import java.util.Random;

// 定义泛型方法
// <T> 泛型标识，具体类型，由调用方法的时候来指定
public class MethodGeneric {

  private Random random = new Random();

  public <T> T getProduct(ArrayList<T> list) {
    return list.get(random.nextInt(list.size()));
  }

  public <T, E> T getProduct2(ArrayList<T> list, E e) {
    return list.get(random.nextInt(list.size()));
  }
}


