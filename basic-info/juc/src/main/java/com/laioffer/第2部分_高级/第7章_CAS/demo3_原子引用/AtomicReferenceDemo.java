package com.laioffer.第2部分_高级.第7章_CAS.demo3_原子引用;

import androidx.annotation.NonNull;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 类的描述: 原子引用
 * Created by 春夏秋冬在中南 on 2023/8/27 14:57
 */
public class AtomicReferenceDemo {

  public static void main(String[] args) {
    AtomicReference<User> atomicReference = new AtomicReference<>();
    User zhangsan = new User("张三", 20);
    User lisi = new User("李四", 30);

    atomicReference.set(zhangsan);
    System.out.println(atomicReference.compareAndSet(zhangsan, lisi) + "\t" + atomicReference.get().toString());
  }
}


class User {
  String userName;
  int age;

  public User(String userName, int age) {
    this.userName = userName;
    this.age = age;
  }

  @NonNull
  @Override
  public String toString() {
    return "User{" +
        "userName='" + userName + '\'' +
        ", age=" + age +
        '}';
  }
}