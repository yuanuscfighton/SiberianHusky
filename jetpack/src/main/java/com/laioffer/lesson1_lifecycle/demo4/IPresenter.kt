package com.laioffer.lesson1_lifecycle.demo4

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * 类的描述: 中间层，继承 LifecycleObserver，提供生命周期回调
 *
 * Created by 春夏秋冬在中南 on 2023/9/13 02:18
 */
interface IPresenter : LifecycleObserver {

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun onResume()

  @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
  fun onPause()
}