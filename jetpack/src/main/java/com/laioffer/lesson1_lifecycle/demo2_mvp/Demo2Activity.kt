package com.laioffer.lesson1_lifecycle.demo2_mvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.laioffer.R

/**
 * 类描述: 版本2: MVP，通过P层 监听生命周期函数
 * created by 春夏秋冬在中南 on 2023/11/28 23:31
 */
class Demo2Activity : AppCompatActivity() {

  private var mMyPresenter: MyPresenter? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.empty_layout)

    mMyPresenter = MyPresenter()
  }

  override fun onResume() {
    super.onResume()
    mMyPresenter?.onResume()
  }

  override fun onPause() {
    super.onPause()
    mMyPresenter?.onPause()
  }
}