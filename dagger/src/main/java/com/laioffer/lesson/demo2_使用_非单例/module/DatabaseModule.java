package com.laioffer.lesson.demo2_使用_非单例.module;


import com.laioffer.lesson.demo2_使用_非单例.obj.DatabaseObject;

import dagger.Module;
import dagger.Provides;

/**
 * 类的描述: 封装包裹：提供Database的对象，i.e. 将Database打包成包裹📦
 * Created by 春夏秋冬在中南 on 2023/9/29 17:14
 */
@Module
public class DatabaseModule {

  // Provides 暴露DatabaseObject对象
  @Provides
  public DatabaseObject provideDatabaseObject() {
    return new DatabaseObject();
  }
}
