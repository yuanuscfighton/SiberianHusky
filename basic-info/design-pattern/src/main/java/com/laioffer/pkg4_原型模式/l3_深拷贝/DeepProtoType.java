package com.laioffer.pkg4_原型模式.l3_深拷贝;

import java.io.Serializable;

import androidx.annotation.NonNull;

public class DeepProtoType implements Serializable, Cloneable {

  public String name;
  // 引用类型
  public DeepCloneableTarget deepCloneableTarget;

  public DeepProtoType() {
    super();
  }

  // 深拷贝：方式1，重写 clone 方法
  @NonNull
  @Override
  protected Object clone() throws CloneNotSupportedException {
    Object deep;
    // 这里完成对 基本类型 或者 String类型 的属性的拷贝
    deep = super.clone();
    // 对引用类型的属性，进行单独处理
    DeepProtoType deepProtoType = (DeepProtoType) deep;
    deepProtoType.deepCloneableTarget = (DeepCloneableTarget) deepCloneableTarget.clone();
    return deepProtoType;
  }
}
