package com.laioffer.lesson1_lifecycle.demo2_mvp

import android.util.Log

/**
 * 类描述: 模拟P层，监听生命周期
 * <p>
 * created by 春夏秋冬在中南 on 2023/11/28 23:28
 */
class MyPresenter {

  private val TAG = MyPresenter::class.java.simpleName

  fun onResume() = Log.e(TAG, "onResume invoked ...")

  fun onPause() = Log.e(TAG, "onPause invoked ...")
}

/*
    P层需要把数据回传给Activity(UI)，如果此时Activity销毁了，就不应该回传数据给Activity了
      因此P层需要监听Activity的声明周期
 */