package com.laioffer.lesson1_lifecycle.demo3_lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.laioffer.R

/**
 * @description: 使用LifecycleOwner和LifecycleObserver实现
 * @date: 2022/9/21 8:04 上午
 */
class Demo3Activity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.empty_layout)

    lifecycle.addObserver(MyObserver())
    lifecycle.addObserver(MyObserver1())
  }
}