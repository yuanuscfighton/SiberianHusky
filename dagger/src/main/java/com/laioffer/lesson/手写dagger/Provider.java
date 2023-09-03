package com.laioffer.lesson.手写dagger;

/**
 * 类的描述: T 就是变化用户注入的对象
 */
public interface Provider<T> {
  T get(); // 跳转到 ComputerModule_ProviderComputerFactory 类中，查看方法的实现
}
