package com.laioffer.lesson1_lifecycle.demo1

import android.util.Log

/**
 * @description: 监听器
 * @date: 2022/9/19 7:55 上午
 */
class MyListener {

  private val TAG = MyListener::class.java.simpleName

  fun start() = Log.e(TAG, "start invoked ...")

  fun stop() = Log.e(TAG, "stop invoked ...")
}