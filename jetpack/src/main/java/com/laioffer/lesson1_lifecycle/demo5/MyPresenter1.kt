package com.laioffer.lesson1_lifecycle.demo5

import android.util.Log

/**
 * 类的描述: 以后可能会有很多子类，例如 MyPresenter、MyPresenter1、MyPresenter2
 *
 * Created by 春夏秋冬在中南 on 2023/9/13 02:20
 */
class MyPresenter1 : IPresenter {
  override fun onResume() {
    Log.d("TAG", "Lifecycle call onResume")
  }

  override fun onPause() {
    Log.d("TAG", "Lifecycle call onPause")
  }
}