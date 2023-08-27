package com.laioffer.lesson.demo1_入门;

import dagger.Component;

/**
 * 类的描述: 快递员角色
 * Created by 春夏秋冬在中南 on 2023/8/24 23:59
 */
@Component(modules = ComputerModule.class) // 一个快递员有很多包裹
public interface ComputerComponent {

  // 快递员需要知道用户的地址，因此需要将地址注入进来
  void injectMainActivity(Demo1Activity activity);

  // 依赖注入: 用户(张三)依赖快递员把快递(电脑)给我。
  // 注入: 完成对象(Computer)的赋值
}
