package com.laioffer.lesson.demo1_入门;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.laioffer.lesson.DevTools;

import javax.inject.Inject;

public class Demo1Activity extends AppCompatActivity {

  @Inject
  Computer mComputer;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    DaggerComputerComponent.create().injectMainActivity(this);
    DevTools.logcatMessage("computer" + mComputer);
  }
}

/*
   第1步：商品
      电脑💻：Computer类

   第2步：封装包裹
      ComputerModule，但需要证明这个是一个包裹，即，添加 @Module 注解

   第3步：寄包裹
      在ComputerModule中，提供getComputer()方法，并标记 @Providers 注解

   第4步：填写地址
      在寄包裹之前，需要告诉快递员地址。即，需要将 MainActivity.this 传到 ComputerComponent 中

   第5步：快递员
      找一个快递员：ComputerComponent 接口

     （1）持有封装好的物品：使用 @Component 注解标记，e.g. @Component(modules = ComputerModule.class)
                         Computer computer = new Computer()
     （2）持有邮寄地址，把封装好的包裹注入到Demo1Activity中：
            e.g. void injectDemo1Activity(Demo1Activity instance)

 */
