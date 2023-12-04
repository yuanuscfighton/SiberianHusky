package com.laioffer.lesson1_lifecycle.demo1

import android.util.Log

/**
 * 类描述: 自定义监听器
 * created by 春夏秋冬在中南 on 2023/11/28 23:21
 */
class MyListener {

  private val TAG = MyListener::class.java.simpleName

  fun start() = Log.e(TAG, "start invoked ...")

  fun stop() = Log.e(TAG, "stop invoked ...")
}