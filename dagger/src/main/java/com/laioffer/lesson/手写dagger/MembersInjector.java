package com.laioffer.lesson.手写dagger;

/**
 * 类的描述: 依赖注入的标准
 */
public interface MembersInjector<T> {
  void injectMembers(T instance);
}

/*
    T == 我们要把对象注入到  MainActivity.this那里
 */