package com.laioffer.lesson.手写dagger2;

// 依赖注入的标准
public interface MembersInjector<T> { // T == 我们要把对象注入到  MainActivity.this那里  MainActivity110那里
    void injectMembers(T instance);
}
