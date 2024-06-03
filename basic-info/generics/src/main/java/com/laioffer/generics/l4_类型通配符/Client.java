package com.laioffer.generics.l4_类型通配符;

// 类型通配符
public class Client {

  public static void main(String[] args) {
    Box<Number> box1 = new Box<>();
    box1.setValue(100);
    showBox(box1);


    Box<Integer> box2 = new Box<>();
    box2.setValue(200);
    // showBox(box2); // 泛型不能用多态的思维理解。即，Integer 是 Number 的子类，但 Box<Integer> 不是 Box<Number> 子类
  }

  public static void showBox(Box<Number> box) {
    Number v1 = box.getValue();
    System.out.println(v1);
  }
}

