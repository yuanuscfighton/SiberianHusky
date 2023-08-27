package com.laioffer.lesson.demo3_局部单例.component;


import com.laioffer.lesson.demo3_局部单例.module.DatabaseModule;
import com.laioffer.lesson.demo3_局部单例.module.HttpModule;
import com.laioffer.lesson.demo3_局部单例.ui.FirstActivity;
import com.laioffer.lesson.demo3_局部单例.ui.SecondActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 类的描述: 快递员
 * Created by 春夏秋冬在中南 on 2023/8/25 07:47
 */
@Singleton
@Component(modules = {HttpModule.class, DatabaseModule.class})
public interface ObjComponents {

  /**
   * 快递员将包裹派送给谁
   *
   * @param activity 被派送的用户
   */
  void injectFirstActivity(FirstActivity activity);

  void injectSecondActivity(SecondActivity activity);
}
