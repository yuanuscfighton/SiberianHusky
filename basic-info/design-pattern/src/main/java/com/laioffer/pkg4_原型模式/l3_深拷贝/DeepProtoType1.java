package com.laioffer.pkg4_原型模式.l3_深拷贝;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DeepProtoType1 implements Serializable, Cloneable {

  public String name;
  // 引用类型
  public DeepCloneableTarget deepCloneableTarget;

  public DeepProtoType1() {
    super();
  }

  // 深拷贝：方式2，通过对象的序列化方式（推荐）
  public Object deepClone() {
    // 创建流对象
    ByteArrayOutputStream bos = null;
    ObjectOutputStream oos = null;
    ByteArrayInputStream bis = null;
    ObjectInputStream ois = null;

    try {
      // 第1步：序列化操作
      bos = new ByteArrayOutputStream();
      oos = new ObjectOutputStream(bos);
      oos.writeObject(this); // 将当前这个对象，以对象流的方式输出

      // 第2步：反序列化
      bis = new ByteArrayInputStream(bos.toByteArray());
      ois = new ObjectInputStream(bis);

      return (DeepProtoType1) ois.readObject();

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } finally {
      try {
        bos.close();
        oos.close();
        bis.close();
        ois.close();
      } catch (Exception e2) {
        System.out.println(e2.getMessage());
      }
    }

  }

}
