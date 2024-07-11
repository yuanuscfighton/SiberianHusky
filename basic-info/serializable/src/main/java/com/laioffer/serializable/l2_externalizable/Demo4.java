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
 * 类描述: 问题3：缺少一个 public 的无参构造函数
 * <p>
 * created by 西伯利亚哈士奇 on 2024/6/16 10:53
 */
public class Demo4 {
  public static void main(String[] args) {
    testExternalizable();
  }

  private static void testExternalizable() {
    User4 user = new User4("zero", 18);
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
      user = (User4) ois.readObject();
      System.out.println("反序列化后: " + user);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

class User4 implements Externalizable {

  // 必须有一个 public 的无参构造函数
//  public User4() {
//  }

  public User4(String name, int age) {
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

  @Override
  public void writeExternal(ObjectOutput out) throws IOException {
    out.writeObject(name);
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
  java.io.InvalidClassException: com.laioffer.serializable.l2_externalizable.User4; no valid constructor
      at java.io.ObjectStreamClass$ExceptionInfo.newInvalidClassException(ObjectStreamClass.java:169)
      at java.io.ObjectStreamClass.checkDeserialize(ObjectStreamClass.java:874)
      at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:2043)
      at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1573)
      at java.io.ObjectInputStream.readObject(ObjectInputStream.java:431)
      at com.laioffer.serializable.l2_externalizable.Demo4.testExternalizable(Demo4.java:41)
      at com.laioffer.serializable.l2_externalizable.Demo4.main(Demo4.java:21)
 */