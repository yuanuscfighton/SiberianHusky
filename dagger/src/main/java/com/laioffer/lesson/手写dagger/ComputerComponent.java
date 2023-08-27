package com.laioffer.lesson.手写dagger;


import dagger.Component;

/**
 * @description 第4个注解 @Component
 * @date 2022/10/6 3:17 下午
 */
@Component(modules = ComputerModule.class)
public interface ComputerComponent { // 快递员

    // 写注入目标  MainActivity的this
    void inject(MainActivity mainActivity);
}
