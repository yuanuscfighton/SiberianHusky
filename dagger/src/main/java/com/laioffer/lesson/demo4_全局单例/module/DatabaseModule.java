package com.laioffer.lesson.demo4_全局单例.module;

import com.laioffer.lesson.demo4_全局单例.obj.DatabaseObject;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 类的描述: 提供Database的对象，i.e. 将Database打包成包裹
 * Created by 春夏秋冬在中南 on 2023/8/25 07:52
 */
@Module
public class DatabaseModule {

  @Singleton
  @Provides
  public DatabaseObject provideDatabaseObject() {
    return new DatabaseObject();
  }
}
