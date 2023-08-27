package com.laioffer.lesson.手写dagger;

/**
 * 类的描述: @Module 用于生成 HttpModule_ProviderHttpObjectFactory
 */
public interface Factory<T> extends Provider<T> { // T == 包裹📦对象，如果HttpObject
}