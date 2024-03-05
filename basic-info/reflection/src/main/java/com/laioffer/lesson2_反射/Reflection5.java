package com.laioffer.lesson2_反射;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射操作注解
 */
public class Reflection5 {
  public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
    Class<?> c = Class.forName("com.laioffer.reflection.Student5");

    // 通过反射获得注解
    Annotation[] annotations = c.getAnnotations();
    for (Annotation ann : annotations) {
      System.out.println(ann); // @com.laioffer.reflection.ClassAnn(value="database_student")
    }

    // 获得注解的value值
    ClassAnn classAnn = c.getAnnotation(ClassAnn.class);
    if (classAnn != null) {
      String v = classAnn.value();
      System.out.println("[获得注解的value值] " + v); // [获得注解的value值] database_student
    }

    // 获得类中指定字段的注解
    Field f = c.getDeclaredField("name");
    FiledAnn filedAnn = f.getAnnotation(FiledAnn.class);
    if (filedAnn != null) {
      System.out.println("[获得类中指定字段的注解] columnName = " + filedAnn.columnName()); // [获得类中指定字段的注解] columnName = database_name
      System.out.println("[获得类中指定字段的注解] type = " + filedAnn.type());             // [获得类中指定字段的注解] type = String
      System.out.println("[获得类中指定字段的注解] length = " + filedAnn.length());         // [获得类中指定字段的注解] length = 15
    }

    Method[] methods = c.getDeclaredMethods();
    for (Method m : methods) {
      if (m.isAnnotationPresent(MethodAnn.class)) {
        System.out.println("[获取注解标记的方法名] " + m.getName()); // [获取注解标记的方法] foo1

        MethodAnn mAnn = m.getAnnotation(MethodAnn.class);
        if (mAnn != null) {
          System.out.println("[获取被注解标记的方法的注解的值] " + mAnn.value()); // [获取被注解标记的方法的注解的值] database_method
        }
      }
    }

  }
}


@ClassAnn("database_student")
class Student5 {

  @FiledAnn(columnName = "database_id", type = "int", length = 10)
  private int id;

  @FiledAnn(columnName = "database_name", type = "String", length = 15)
  private String name;

  @FiledAnn(columnName = "database_age", type = "int", length = 2)
  private int age;

  @MethodAnn("database_method")
  public void foo1() {
    System.out.println("id = " + id + ", name = " + name + ", age = " + age);
  }
}

/**
 * 类名的注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface ClassAnn {

  String value();
}

/**
 * 属性的注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FiledAnn {

  String columnName();

  String type();

  int length();
}

/**
 * 方法的注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface MethodAnn {
  String value();
}



