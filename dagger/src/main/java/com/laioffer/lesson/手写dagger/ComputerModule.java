package com.laioffer.lesson.手写dagger;

import dagger.Module;
import dagger.Provides;

/**
 * @description 第2个注解: @Module
 * @date 2022/10/6 11:38 上午
 */
@Module
public class ComputerModule {

  /**
   * 第3个注解: Provider
   */
  @Provides
  public Computer providerStudent() {
    return new Computer();
  }
}
