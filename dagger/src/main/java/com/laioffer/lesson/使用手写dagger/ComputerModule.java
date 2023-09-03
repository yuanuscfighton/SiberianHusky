package com.laioffer.lesson.使用手写dagger;

import com.laioffer.lesson.手写dagger.annotation.Module;
import com.laioffer.lesson.手写dagger.annotation.Provides;

/**
 * 类的描述: 封装包裹
 */
@Module // 第2个注解: @Module
public class ComputerModule {

  /**
   * 第3个注解: @Provider
   */
  @Provides
  public Computer providerStudent() {
    return new Computer();
  }
}

/*
    生成 ComputerModule_ProviderComputerFactory 类
 */
