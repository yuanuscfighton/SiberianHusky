package com.laioffer.lesson2_反射;

/**
 * 类描述:
 * <p>
 * created by 春夏秋冬在中南 on 2024/3/1 08:16
 */
public class Reflection1 {
  public static void main(String[] args) throws ClassNotFoundException {
    Class<?> c1 = Class.forName("com.laioffer.lesson2_反射.User");
    System.out.println(c1); // class com.laioffer.lesson2_反射.User

    Class<?> c2 = Class.forName("com.laioffer.lesson2_反射.User");
    Class<?> c3 = Class.forName("com.laioffer.lesson2_反射.User");
    Class<?> c4 = Class.forName("com.laioffer.lesson2_反射.User");

    // 一个类在内存中只有一个Class对象
    // 一个类被加载后，类的整个结构都会被封装在Class对象中
    System.out.println(c2.hashCode()); // 1554874502
    System.out.println(c3.hashCode()); // 1554874502
    System.out.println(c4.hashCode()); // 1554874502
  }
}

class User {
  private String name;
  private int age;

  public User() {
  }

  public User(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "User{" +
        "name='" + name + '\'' +
        ", age=" + age +
        '}';
  }
}
