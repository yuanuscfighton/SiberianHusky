package com.laioffer.lesson.手写dagger2;

/**
 * T 就是变化用户注入的对象
 *
 * @param <T>
 */
public interface Provider<T> {
  T get();
}
