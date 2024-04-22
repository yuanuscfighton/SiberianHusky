package com.laioffer;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MainApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // 初始化 Fresco
    Fresco.initialize(this);
  }
}
