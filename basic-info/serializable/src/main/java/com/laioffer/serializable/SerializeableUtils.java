package com.laioffer.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeableUtils {

  public static <T> byte[] serialize(T t) throws Exception {


    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(out);
    oos.writeObject(t);
    return out.toByteArray();
  }

  public static <T> T deserialize(byte[] bytes) throws Exception {
    //TODO:
    ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
    T t = (T) ois.readObject();
    return t;
  }

  /**
   * 序列化后存储到本地
   */
  synchronized public static boolean saveObject(Object obj, String path) {
    if (obj == null) {
      return false;
    }
    ObjectOutputStream oos = null;
    try {
      // 创建序列化流对象
      FileOutputStream fos = new FileOutputStream(path);
      oos = new ObjectOutputStream(fos);
      oos.writeObject(obj);
      oos.close();
      return true;
    } catch (IOException e) {
      System.out.println(e.getMessage());
    } finally {
      if (oos != null) {
        try {
          oos.close(); // 释放资源
        } catch (IOException e) {
          System.out.println(e.getMessage());
        }
      }
    }
    return false;
  }

  /**
   * 反序列化对象
   */
  @SuppressWarnings("unchecked")
  synchronized public static <T> T readObject(String path) {
    ObjectInputStream ojs = null;
    try {
      // 创建反序列化对象
      FileInputStream fis = new FileInputStream(path);
      ojs = new ObjectInputStream(fis);
      // 还原对象
      return (T) ojs.readObject();
    } catch (IOException | ClassNotFoundException e) {
      System.out.println(e.getMessage());
    } finally {
      if (ojs != null) {
        try {
          ojs.close();// 释放资源
        } catch (IOException e) {
          System.out.println(e.getMessage());
        }
      }
    }
    return null;
  }

}
