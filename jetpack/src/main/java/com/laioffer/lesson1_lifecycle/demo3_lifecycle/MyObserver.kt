package com.laioffer.lesson1_lifecycle.demo3_lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * 类的描述: 观察者 -- 眼睛
 *
 * Created by 春夏秋冬在中南 on 2023/9/12 07:48
 */
class MyObserver : LifecycleObserver {

  private val TAG = MyObserver::class.java.simpleName

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun connectListener() = Log.e(TAG, "connectListener run ...")

  @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
  fun disconnectListener() = Log.e(TAG, "disconnectListener run ...")
}