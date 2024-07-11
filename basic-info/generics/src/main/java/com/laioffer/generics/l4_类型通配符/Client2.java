package com.laioffer.generics.l4_类型通配符;

/**
 * 类描述:
 * <p>
 * created by 西伯利亚哈士奇 on 2024/6/1 17:45
 */
public class Client2 {
  public static void main(String[] args) {
    Box<Number> box1 = new Box<>();
    box1.setValue(100);
    showBox(box1);


    Box<Integer> box2 = new Box<>();
    box2.setValue(200);
    showBox(box2);
  }

  // ? extends Number 可以传 Number 及 Number 的子类
  public static void showBox(Box<? extends Number> box) {
    Number v1 = box.getValue();
    System.out.println(v1);
  }
}
