package com.laioffer.lesson.demo2_使用_非单例.module;


import com.laioffer.lesson.demo2_使用_非单例.obj.DatabaseObject;

import dagger.Module;
import dagger.Provides;

/**
 * @description 提供Database的对象，i.e. 将Database打包成包裹
 * @date 2022/10/04 4:03 下午
 */
@Module
public class DatabaseModule {

  // Provides 暴露DatabaseObject对象
  @Provides
  public DatabaseObject provideDatabaseObject() {
    return new DatabaseObject();
  }
}
