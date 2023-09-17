package com.laioffer.lesson2_livedata.demo1

import androidx.lifecycle.MutableLiveData

object MyLiveData { // 单例

  // 懒加载
  val info1: MutableLiveData<String> by lazy { MutableLiveData() }

}