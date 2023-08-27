package com.laioffer.lesson.使用手写dagger;


import com.laioffer.lesson.手写dagger.annotation.Component;

/**
 * 类的描述: 快递员
 */
@Component(modules = ComputerModule.class) // 第4个注解 @Component
public interface ComputerComponent {

  // 写注入目标  MainActivity的this
  void inject(MainActivity mainActivity);
}

/*
    1. @Component 注解生成 DaggerComputerComponent 类
    2. ComputerComponent 左手拿 ComputerModule，右手拿 MainActivity.this
 */