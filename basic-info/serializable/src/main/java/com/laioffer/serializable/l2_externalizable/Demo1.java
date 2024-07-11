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
 * Externalizable 接口的作用
 */
public class Demo1 {

  public static void main(String[] args) {
    testExternalizable();
  }

  private static void testExternalizable() {
    User1 user = new User1("zero", 18);
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
      user = (User1) ois.readObject();
      System.out.println("反序列化后: " + user);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

class User1 implements Externalizable {

  //必须要一个public的无参构造函数
  public User1() {
  }

  public User1(String name, int age) {
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