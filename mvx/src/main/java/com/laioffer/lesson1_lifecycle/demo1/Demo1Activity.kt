package com.laioffer.lesson1_lifecycle.demo1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.laioffer.mvx.R
import com.laioffer.my_mvx.lesson1_lifecycle.demo1.MyListener

/**
 * 类的描述: 版本1: 监听器监听生命周期
 * Created by 春夏秋冬在中南 on 2023/9/12 07:41
 */
class Demo1Activity : AppCompatActivity() {

  /* 如果没有Lifecycle，就需要自定义监听器，监听生命周期变化 */
  private var myListener: MyListener? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.empty_layout)

    myListener = MyListener()
  }

  override fun onStart() {
    super.onStart()
    myListener?.start()
  }

  override fun onStop() {
    super.onStop()
    myListener?.stop()
  }
}