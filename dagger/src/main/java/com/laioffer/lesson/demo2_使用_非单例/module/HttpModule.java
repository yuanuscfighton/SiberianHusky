package com.laioffer.lesson.demo2_使用_非单例.module;

import com.laioffer.lesson.demo2_使用_非单例.obj.HttpObject;

import dagger.Module;
import dagger.Provides;

/**
 * @description 提供Http的对象，i.e. 将Http打包成包裹
 * @date 2022/10/04 4:03 下午
 */
@Module
public class HttpModule {

  // Provides 暴露HttpObject对象
  @Provides
  public HttpObject provideHttpObject() {
    return new HttpObject();
  }
}
