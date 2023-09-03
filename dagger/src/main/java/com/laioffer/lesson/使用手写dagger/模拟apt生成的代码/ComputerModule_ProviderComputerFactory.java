package com.laioffer.lesson.使用手写dagger.模拟apt生成的代码;


import com.laioffer.lesson.使用手写dagger.Computer;
import com.laioffer.lesson.使用手写dagger.ComputerModule;
import com.laioffer.lesson.手写dagger.Factory;

import dagger.internal.Preconditions;

/**
 * 通过第2个注解@Module和第3个注解@Provider生成这个类
 */
public class ComputerModule_ProviderComputerFactory implements Factory<Computer> {

  // 包裹
  private final ComputerModule computerModule;

  public ComputerModule_ProviderComputerFactory(ComputerModule computerModule) {
    this.computerModule = computerModule;
  }

  @Override
  public Computer get() {
    return Preconditions.checkNotNull(
        computerModule.providerStudent(), "你个货乱搞 无法studentModule.providerStudent()");
  }

  // 暴露给外界用的
  public static Factory<Computer> create(ComputerModule module) {
    return new ComputerModule_ProviderComputerFactory(module);
  }
}
