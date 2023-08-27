package com.laioffer.lesson.demo2_使用_非单例.app;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

// hilt 基本上都要 用 Application来辅助
@HiltAndroidApp
public class MyApplication extends Application {

}
