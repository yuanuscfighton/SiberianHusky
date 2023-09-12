package com.laioffer.lesson1_lifecycle.demo5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.laioffer.mvx.R

/**
 * 类的描述:
 * Created by 春夏秋冬在中南 on 2023/9/13 02:22
 */
class Demo5Activity : AppCompatActivity() {

  private var myPresenter: IPresenter? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.empty_layout)

    myPresenter = MyPresenter()
    lifecycle.addObserver(myPresenter!!)
  }
}