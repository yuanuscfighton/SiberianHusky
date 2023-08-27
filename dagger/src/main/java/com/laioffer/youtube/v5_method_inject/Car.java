package com.laioffer.youtube.v5_method_inject;

import javax.inject.Inject;

public class Car {

  @Inject
  Engine mEngine;
  private Wheels mWheels;

  @Inject
  public Car(Wheels wheels) {
    mWheels = wheels;
  }

  // We need a Remote object, call setListener on it, and pass the car instance to itself with "this".
  // This is a good case to use "Method Injection". If you have to pass the injected object that served to dependency.
  // Because if you execute "remote.setListener(this)" method in the constructor then you pass a reference to an object that is not yet fully instantiated,
  // because the constructor hasn't finished yet. Generally you should avoid this and only pass this after the constructor.
  //
  // By add @Inject on a method, Dagger will automatically executed after the constructor finished, this means we don't have to call this method, Dagger does this for us.
  // Dagger will create Remote instance and execute "remote.setListener(this)".
  // This method cannot be private, otherwise Dagger cannot call it for you.
  // you can also inject multiple methods in which case Dagger will call one after and another.
  @Inject
  public void enableRemote(Remote remote) {
    remote.setListener(this);
  }

  public void drive() {
    // Log.d(Dagger2LogTag.CAR5_LOG_TAG, "driving...");
  }


  // Dagger执行的顺序: call constructor --> call inject field --> call inject method.

  // Why is our Engine field automatically injected? Why we have to call "inject()" to inject out "mCar" member in our Car5Activity?
  // Because field and method injection are only automatically executed if we also do a constructor injection. This is what triggers the whole process.
  // In Car5Activity, we cannot do constructor injection, so we have to trigger the injection process manually by calling "inject()" on the "component",
  // and passing the "Car5Activity this" ("component.inject(this);"). This means we could also put an injected method into our Car5Activity, which would
  // then also be triggered by this "inject()".
  // Usually you don't do Method Injection in Activities.
  //
  //
  // If we have constructor injection, fields and methods are also injected automatically after the constructor finished.

}
