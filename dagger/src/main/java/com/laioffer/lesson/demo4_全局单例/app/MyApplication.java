package com.laioffer.lesson.demo4_全局单例.app;

import android.app.Application;

import com.laioffer.lesson.demo4_全局单例.component.DaggerObjComponents;
import com.laioffer.lesson.demo4_全局单例.component.ObjComponents;
import com.laioffer.lesson.demo4_全局单例.module.DatabaseModule;
import com.laioffer.lesson.demo4_全局单例.module.HttpModule;


public class MyApplication extends Application {

  private ObjComponents mObjComponents;

  @Override
  public void onCreate() {
    super.onCreate();

    // 只构建(初始化)一次，保证是全局单例的
    mObjComponents =
        DaggerObjComponents.builder()
            .httpModule(new HttpModule())
            .databaseModule(new DatabaseModule())
            .build();
  }

  public ObjComponents getAppComponent() {
    return mObjComponents;
  }
}
