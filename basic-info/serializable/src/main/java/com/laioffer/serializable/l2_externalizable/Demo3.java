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
 * 类描述: 错误2：writeExternal 和 readExternal 中的成员变量顺序不一致
 * <p>
 * created by 西伯利亚哈士奇 on 2024/6/16 10:47
 */
public class Demo3 {

  public static void main(String[] args) {
    testExternalizable();
  }

  private static void testExternalizable() {
    User3 user = new User3("zero", 18);
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
      user = (User3) ois.readObject();
      System.out.println("反序列化后: " + user);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

class User3 implements Externalizable {

  public User3() {
  }

  public User3(String name, int age) {
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

  /* writeExternal 和 readExternal 方法中的变量顺序必须一致 */
  @Override
  public void writeExternal(ObjectOutput out) throws IOException {
    out.writeInt(age);
    out.writeObject(name);
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
      at com.laioffer.serializable.l2_externalizable.User2.readExternal(Demo3.java:81)
      at java.io.ObjectInputStream.readExternalData(ObjectInputStream.java:2118)
      at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:2067)
      at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1573)
      at java.io.ObjectInputStream.readObject(ObjectInputStream.java:431)
      at com.laioffer.serializable.l2_externalizable.Demo3.testExternalizable(Demo3.java:42)
      at com.laioffer.serializable.l2_externalizable.Demo3.main(Demo3.java:22)

 */