package com.laioffer.lesson1_lifecycle.demo4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.laioffer.R

/**
 * 类描述: 被观察者 Observable
 *
 * created by 春夏秋冬在中南 on 2023/11/29 08:17
 */
class Demo4Activity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.empty_layout)

    lifecycle.addObserver(MyLocationListener())
  }
}