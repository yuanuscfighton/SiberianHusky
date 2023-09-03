package com.laioffer.youtube.demo1;

/**
 * 类的描述: 汽车🚗
 * Created by 春夏秋冬在中南 on 2023/8/13 00:10
 */
public class Car {

  Engine mEngine;
  Wheels mWheels;

  public Car() {
    mEngine = new Engine();
    mWheels = new Wheels();
  }

  public void drive() {

  }
}

/*
  在Car的构造函数中，实例化(new)了 Engine 和 Wheels 对象。
每次实例化Car对象的时候，都将由Car内部创建一个新的 Engine 和 Wheels。

  正确的做法应该是在别处实例化 Engine 和 Wheels
 */
