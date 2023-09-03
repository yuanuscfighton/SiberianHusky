package com.laioffer.lesson.demo4_全局单例.module;

import com.laioffer.lesson.demo4_全局单例.obj.HttpObject;

import dagger.Module;
import dagger.Provides;

/**
 * 类的描述: 提供Http的对象，i.e. 将Http打包成包裹
 * Created by 春夏秋冬在中南 on 2023/8/25 07:51
 */
@Module
public class HttpModule {
  @Provides
  public HttpObject provideHttpObject() {
    return new HttpObject();
  }
}
