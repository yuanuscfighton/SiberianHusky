package com.laioffer.lesson.手写dagger;

/**
 * 类的描述: @Singleton 单例，对象初始化一次
 */
public final class DoubleCheck<T> implements Provider<T> {

  // 为了单例的检查而已
  private static final Object UNINITIALIZED = new Object();

  // 最上层实例化对象的 接口
  private volatile Provider<T> provider;
  private volatile Object instance = UNINITIALIZED;

  private DoubleCheck(Provider<T> provider) {
    this.provider = provider;
  }

  /**
   * 单例，对象初始化一次
   */
  @Override
  public T get() {
    Object result = instance; // instance = new Student();
    if (result == UNINITIALIZED) {
      synchronized (this) {
        result = instance;
        if (result == UNINITIALIZED) {
          instance = result = provider.get(); // new Student();
          provider = null;
        }
      }
    }
    return (T)result;
  }

  public static <T> Provider<T> provider(Provider<T> delegate) {
    Preconditions.checkNotNull(delegate); // 工具类，检查
    if (delegate instanceof DoubleCheck) {
      return delegate;
    }
    return new DoubleCheck<T>(delegate);
  }
}
