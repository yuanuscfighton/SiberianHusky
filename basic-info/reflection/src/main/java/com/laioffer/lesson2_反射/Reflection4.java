package com.laioffer.lesson2_反射;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 获取泛型信息
 */
public class Reflection4 {

  public static void main(String[] args) throws NoSuchMethodException {
    // 返回一个方法，然后再获取方法中的参数
    Method method = Reflection4.class.getMethod("foo1", Map.class, List.class);

    // 获取参数泛型信息
    Type[] genericParameterTypes = method.getGenericParameterTypes();
    for (Type genericParamType : genericParameterTypes) {
      System.out.println("#" + genericParamType);
      if (genericParamType instanceof ParameterizedType) {
        Type[] actualTypeArguments = ((ParameterizedType) genericParamType).getActualTypeArguments();
        for (Type actualTypeArgument : actualTypeArguments) {
          System.out.println(actualTypeArgument);
        }
      }
    }

    System.out.println("====================");

    method = Reflection4.class.getMethod("foo2", null);
    Type genericReturnType = method.getGenericReturnType();
    if (genericReturnType instanceof ParameterizedType) {
      Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
      for (Type actualTypeArgument : actualTypeArguments) {
        System.out.println(actualTypeArgument);
      }
    }
  }

  public void foo1(Map<String, User> map, List<User> list) {
    System.out.println("foo1");
  }

  public Map<String, User> foo2() {
    System.out.println("foo2");
    return null;
  }
}
