package com.laioffer.lesson1_lifecycle.demo4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.laioffer.mvx.R

/**
 * @description: 被观察者 Observable
 * @date: 2022/9/21 11:02 下午
 */
class Demo4Activity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.empty_layout)

    lifecycle.addObserver(MyLocationListener())
  }
}