package com.laioffer.my_mvx.lesson1_lifecycle.demo3_lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * @description:
 * @date: 2022/9/21 8:05 上午
 */
class MyObserver : LifecycleObserver {

  private val TAG = MyObserver::class.java.simpleName

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun connectListener() = Log.e(TAG, "connectListener run ...")

  @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
  fun disconnectListener() = Log.e(TAG, "disconnectListener run ...")
}