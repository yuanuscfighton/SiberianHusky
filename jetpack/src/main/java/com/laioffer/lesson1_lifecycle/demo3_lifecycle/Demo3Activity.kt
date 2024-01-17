package com.laioffer.lesson1_lifecycle.demo3_lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.laioffer.R

/**
 * 类描述: 使用LifecycleOwner和LifecycleObserver实现
 * <p>
 * created by 春夏秋冬在中南 on 2023/11/29 08:09
 */
class Demo3Activity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.empty_layout)

    lifecycle.addObserver(MyObserver())
    lifecycle.addObserver(MyObserver1())
  }
}