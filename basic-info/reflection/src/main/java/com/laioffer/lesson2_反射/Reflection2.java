package com.laioffer.lesson2_反射;

import androidx.annotation.NonNull;

/**
 * 获取Class对象
 */
public class Reflection2 {
  public static void main(String[] args) throws ClassNotFoundException {
    Person s = new Student();
    System.out.println("这个人是 " + s.name);

    // 方式1：通过对象获得
    Class<?> c1 = s.getClass();
    System.out.println(c1.hashCode());

    // 方式2：forName获得
    Class<?> c2 = Class.forName("com.laioffer.reflection.Student");
    System.out.println(c2.hashCode());

    // 方式3：通过类名.class获得
    Class<?> c3 = Student.class;
    System.out.println(c3.hashCode());


    // 基本内置类型的包装类，都有一个Type属性
    Class<?> intType = Integer.class;
    System.out.println(intType);

    // 获得父类类型
    Class<?> clz = c1.getSuperclass();
    System.out.println(clz);
  }
}

class Person {
  public String name;

  public Person() {
  }

  @NonNull
  @Override
  public String toString() {
    return "Person{" +
        "name='" + name + '\'' +
        '}';
  }
}

class Student extends Person {
  public Student() {
    this.name = "学生";
  }
}

class Teacher extends Person {
  public Teacher() {
    this.name = "老师";
  }
}
