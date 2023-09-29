package com.laioffer.lesson.demo1_入门;

import dagger.Component;

/**
 * 类的描述: 快递员角色
 * Created by 春夏秋冬在中南 on 2023/8/24 23:59
 */
@Component(modules = ComputerModule.class) // 一个快递员有很多包裹
public interface ComputerComponent {

  // 快递员需要知道用户的地址，因此填写地址，即把 Demo1Activity 引用作为参数传进来
  void injectMainActivity(Demo1Activity activity);

}

/*
  第3步：快递员 - ComputerComponent
    （1）持有封装好的物品：可以接收很多包裹 ← 标记📌 @Component(modules = ComputerModule.class)
            Computer computer = new Computer()

    （2）持有邮寄地址，把封装好的包裹注入到Demo1Activity中：
            void injectDemo1Activity(Demo1Activity instance)


  依赖注入：李四 依赖快递员 把电脑 派送过来
     注入：完成对象（Computer）的赋值
 */