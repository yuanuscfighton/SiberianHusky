package com.laioffer.lesson1_lifecycle.demo5

import android.util.Log

/**
 * 类的描述: 版本5: 接口监听法
 * Created by 春夏秋冬在中南 on 2023/9/13 02:20
 */
class MyPresenter : IPresenter {
  override fun onResume() {
    Log.d("TAG", "Lifecycle call onResume")
  }

  override fun onPause() {
    Log.d("TAG", "Lifecycle call onPause")
  }
}