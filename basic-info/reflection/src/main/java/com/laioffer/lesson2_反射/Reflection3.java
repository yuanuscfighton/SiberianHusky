package com.laioffer.lesson2_反射;

import java.lang.annotation.ElementType;

/**
 * 所有类型的Class
 */
public class Reflection3 {
  public static void main(String[] args) {
    // 1.一个类的Class
    Class<?> c1 = Object.class;

    // 2.接口的Class
    Class<?> c2 = Comparable.class;

    // 3.一位数组
    Class<?> c3 = String[].class;

    // 4.二维数组
    Class<?> c4 = int[][].class;

    // 5.注解
    Class<?> c5 = Override.class;

    // 6.枚举
    Class<?> c6 = ElementType.class;

    // 7.基本数据类型
    Class<?> c7 = Integer.class;

    // 8.void
    Class<?> c8 = void.class;

    // 9.Class
    Class<?> c9 = Class.class;

    System.out.println("c1= " + c1);
    System.out.println("c2= " + c2);
    System.out.println("c3= " + c3);
    System.out.println("c4= " + c4);
    System.out.println("c5= " + c5);
    System.out.println("c6= " + c6);
    System.out.println("c7= " + c7);
    System.out.println("c8= " + c8);
    System.out.println("c9= " + c9);

    // 只要元素类型与维度一样，就是同一个Class对象
    int[] a = new int[10];
    int[] b = new int[100];

    System.out.println(a.getClass().hashCode());
    System.out.println(b.getClass().hashCode());

  }
}
