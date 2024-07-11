package com.laioffer.generics.l1_泛型类;

import androidx.annotation.NonNull;

public class ClassGeneric {
  public static void main(String[] args) {
    Bean<String> b1 = new Bean<>("aaa");
    Bean<Integer> b2 = new Bean<>(100);

    System.out.println(b1.getClass()); // class com.laioffer.generics.l1_泛型类.Bean
    System.out.println(b2.getClass()); // class com.laioffer.generics.l1_泛型类.Bean

    System.out.println(b1.getClass() == b2.getClass()); // true
  }

}


class Bean<T> {
  private T m;


  public Bean(T m) {
    this.m = m;
  }

  public T getM() {
    return m;
  }

  public void setM(T m) {
    this.m = m;
  }

  @NonNull
  @Override
  public String toString() {
    return "Bean{" +
        "m=" + m +
        '}';
  }
}
