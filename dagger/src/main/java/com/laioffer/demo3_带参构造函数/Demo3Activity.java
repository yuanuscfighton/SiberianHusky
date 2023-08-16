package com.laioffer.demo3_带参构造函数;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.laioffer.demo3_带参构造函数.engine.Block;
import com.laioffer.demo3_带参构造函数.engine.Cylinders;
import com.laioffer.demo3_带参构造函数.engine.SparkPlugs;
import com.laioffer.demo3_带参构造函数.wheel.Rims;
import com.laioffer.demo3_带参构造函数.wheel.Tires;

/**
 * 类的描述: <a href="https://www.bilibili.com/video/BV1mL4y1B76c/">依赖注入的介绍</a>
 * Created by 春夏秋冬在中南 on 2023/8/13 13:33
 */
public class Demo3Activity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    /*
       我们必须在这里初始化这些对象，然后将它们传给Engine 和 Wheels，
       缺点: 目前这种手动依赖注入，会污染调用的地方。因为一旦有个构造函数的入参发生变化，所有的地方都需要修改

       因此引入Dagger2来解决这些问题，我们只需要告诉Dagger在什么时候去创建这些对象
     */

    Block block = new Block();
    Cylinders cylinders = new Cylinders();
    SparkPlugs sparkPlugs = new SparkPlugs();
    Rims rims = new Rims();
    Tires tires = new Tires();

    Engine engine = new Engine(block, cylinders, sparkPlugs);
    Wheels wheels = new Wheels(rims, tires);

    Car car = new Car(engine, wheels);

  }
}
