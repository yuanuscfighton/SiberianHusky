package com.laioffer.generics.l4_类型通配符;

/**
 * 类描述:
 * <p>
 * created by 西伯利亚哈士奇 on 2024/6/1 17:46
 */
public class Box<E> {
  private E first;

  public E getValue() {
    return first;
  }

  public void setValue(E first) {
    this.first = first;
  }
}
