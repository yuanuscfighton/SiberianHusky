package com.laioffer.generics.l2_泛型接口;


public class InterfaceGeneric {
}

// 实现泛型接口的类，不是泛型类，需要明确泛型接口的数据类型
// 如果不写，则是 Object 类型
class Apple implements Generator<String> {
  @Override
  public String getKey() {
    return "hello";
  }
}


// 泛型接口的实现类，是一个泛型类，需要保证实现接口的泛型类泛型标识 包含泛型接口的泛型标识
class Pair<T, E> implements Generator<T> {

  private T mKey;
  private E mValue;

  public Pair(T key, E value) {
    mKey = key;
    mValue = value;
  }

  @Override
  public T getKey() {
    return mKey;
  }

  public E getValue() {
    return mValue;
  }
}


interface Generator<T> {
  T getKey();
}
