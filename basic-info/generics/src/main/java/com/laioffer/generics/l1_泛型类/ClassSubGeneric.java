package com.laioffer.generics.l1_泛型类;

public class ClassSubGeneric {
  public static void main(String[] args) {
    // 创建子类对象的时候，是会先创建父类对象
    ChildFirst<String> c = new ChildFirst<>();
  }

}

class Parent<E> {

  private E value;

  public E getValue() {
    return value;
  }

  public void setValue(E value) {
    this.value = value;
  }
}

// 泛型类派生子类，子类也是泛型类，那么子类的泛型标识要和父类一致
class ChildFirst<T> extends Parent<T> {

  @Override
  public T getValue() {
    return super.getValue();
  }
}

