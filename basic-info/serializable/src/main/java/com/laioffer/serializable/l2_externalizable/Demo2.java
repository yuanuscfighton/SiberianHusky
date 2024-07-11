package com.laioffer.serializable.l2_externalizable;

import androidx.annotation.NonNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * 类描述: 错误1：writeExternal 和 readExternal 中的成员变量个数不一致
 * <p>
 * created by 西伯利亚哈士奇 on 2024/6/16 10:47
 */
public class Demo2 {

  public static void main(String[] args) {
    testExternalizable();
  }

  private static void testExternalizable() {
    User2 user = new User2("zero", 18);
    System.out.println("原始数据: " + user);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ObjectOutputStream oos;
    byte[] userData = null;
    try {
      oos = new ObjectOutputStream(out);
      oos.writeObject(user);
      userData = out.toByteArray();
    } catch (IOException e) {
      e.printStackTrace();
    }

    ObjectInputStream ois;
    try {
      ois = new ObjectInputStream(new ByteArrayInputStream(userData));
      user = (User2) ois.readObject();
      System.out.println("反序列化后: " + user);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

class User2 implements Externalizable {

  public User2() {
  }

  public User2(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String name;
  public int age;

  @NonNull
  @Override
  public String toString() {
    return "User{" +
        "name='" + name + '\'' +
        ", age=" + age +
        '}';
  }

  /* writeExternal 和 readExternal 方法中的变量必须成对的 */
  @Override
  public void writeExternal(ObjectOutput out) throws IOException {
//    out.writeObject(name);
    out.writeInt(age);
  }

  @Override
  public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    name = (String) in.readObject();
    age = in.readInt();
  }
}

/*
  原始数据: User{name='zero', age=18}
  java.io.OptionalDataException
    at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1585)
    at java.io.ObjectInputStream.readObject(ObjectInputStream.java:431)
    at com.laioffer.serializable.l2_externalizable.User1.readExternal(demo2.java:81)
    at java.io.ObjectInputStream.readExternalData(ObjectInputStream.java:2118)
    at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:2067)
    at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1573)
    at java.io.ObjectInputStream.readObject(ObjectInputStream.java:431)
    at com.laioffer.serializable.l2_externalizable.demo2.testExternalizable(demo2.java:42)
    at com.laioffer.serializable.l2_externalizable.demo2.main(demo2.java:22)
 */