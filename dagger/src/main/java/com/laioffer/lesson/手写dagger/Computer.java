package com.laioffer.lesson.手写dagger;

import javax.inject.Inject;

// 第1个注解
// 电脑
public class Computer {

  // 此处Inject注解的含义: 代码Computer对象是被注入的来源，被注入到MainActivity中
  @Inject
  public Computer() {}

}





