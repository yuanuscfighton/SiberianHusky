package com.laioffer.lesson.使用手写dagger;

import com.laioffer.lesson.手写dagger.annotation.Inject;

// 第1个注解
// 电脑
public class Computer {

  // 此处Inject注解的含义: 代码Computer对象是被注入的来源，i.e. 被注入到MainActivity中
  @Inject
  public Computer() {
  }

}

/*
    @Inject 生成 Computer_Factory 类
 */