package com.laioffer.pkg4_原型模式.l3_深拷贝;

import java.io.Serializable;

import androidx.annotation.NonNull;

public class DeepCloneableTarget implements Serializable, Cloneable {

  private static final long serialVersionUID = 1L;

  private String mCloneName;

  private String mCloneClz;

  public DeepCloneableTarget(String cloneName, String cloneClz) {
    mCloneName = cloneName;
    mCloneClz = cloneClz;
  }

  // 因为该类中的属性都是 String 类型，因为我们这里使用默认的 clone 方法即可
  @NonNull
  @Override
  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}
