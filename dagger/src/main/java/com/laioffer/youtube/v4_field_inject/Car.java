package com.laioffer.youtube.v4_field_inject;

import javax.inject.Inject;

public class Car {

  private Engine mEngine;
  private Wheels mWheels;

  // 如果没有加@Inject注解，会报错
  // error_msg= Car cannot be provided without an @Inject constructor or an @Provides-annotated method.
  @Inject
  // @Inject: Dagger will know this is the way to initiate Car object.
  // This is called Constructor Injection, because Dagger will later provide this dependencies.
  //
  // Now Dagger can inject this constructor.
  // But Dagger don't know how to initiate Engine and Wheels, so we need to add @Inject annotation.
  public Car(Engine engine, Wheels wheels) {
    mEngine = engine;
    mWheels = wheels;
  }

  public void drive() {
    // Log.d(Dagger2LogTag.CAR4_LOG_TAG, "driving...");
  }
}
