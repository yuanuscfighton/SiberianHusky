package com.laioffer.lesson.使用手写dagger.模拟apt生成的代码;

import com.laioffer.lesson.使用手写dagger.Computer;
import com.laioffer.lesson.手写dagger.Factory;

/**
 * 类的描述: @Inject 注解生成的类
 */
public enum Computer_Factory implements Factory<Computer> {

  INSTANCE;

  @Override
  public Computer get() {
    return new Computer();
  }

  public static Factory<Computer> create() {
    return INSTANCE;
  }
}
