package com.laioffer.lesson.demo1_入门;

import dagger.Module;
import dagger.Provides;

/**
 * 类的描述: 包裹: 商家将电脑打包成包裹
 * Created by 春夏秋冬在中南 on 2023/8/24 23:21
 */
@Module
public class ComputerModule {

  // 对外暴露包裹
  @Provides
  public Computer getComputer() {
    // Dagger解耦:
    // 好处1: 隔离，对Computer对象进行了封装包裹管理，发生变化的地方只有这一处了
    return new Computer();
  }
}
