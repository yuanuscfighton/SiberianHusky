package com.laioffer.lesson.demo2_使用_非单例.module;

import com.laioffer.lesson.demo2_使用_非单例.obj.HttpObject;

import dagger.Module;
import dagger.Provides;

/**
 * 类的描述: 封装包裹，提供http对象，i.e. 将http打包成包裹📦
 * Created by 春夏秋冬在中南 on 2023/9/29 17:15
 */
@Module
public class HttpModule {

  // Provides 暴露HttpObject对象
  @Provides
  public HttpObject provideHttpObject() {
    return new HttpObject();
  }
}
