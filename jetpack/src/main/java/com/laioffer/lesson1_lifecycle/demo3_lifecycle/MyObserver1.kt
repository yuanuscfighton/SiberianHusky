package com.laioffer.lesson1_lifecycle.demo3_lifecycle

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * 类描述: 观察者，继承 DefaultLifecycleObserver
 *
 * created by 春夏秋冬在中南 on 2023/11/29 08:17
 */
class MyObserver1 : DefaultLifecycleObserver { // 使用 DefaultLifecycleObserver 好处是 可以拿到Activity/Fragment的所有环境

  private val TAG = MyObserver1::class.java.simpleName

  override fun onCreate(owner: LifecycleOwner) {
    super.onCreate(owner)
    Log.e(TAG, "onCreate()")
  }

  override fun onResume(owner: LifecycleOwner) {
    super.onResume(owner)
    Log.e(TAG, "onResume()")
  }
}