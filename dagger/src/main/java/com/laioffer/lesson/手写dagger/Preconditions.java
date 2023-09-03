package com.laioffer.lesson.手写dagger;

/**
 * 类的描述: 工具类，检测是否是null
 */
public class Preconditions {

  public static <T> T checkNotNull(T value) {
    if (null == value) {
      throw new NullPointerException("value is null exception...");
    }
    return value;
  }

  public static <T> T checkNotNull(T value, String errorMessage) {
    if (null == value) {
      throw new IllegalArgumentException(errorMessage);
    }
    return value;
  }
}
