package com.laioffer.lesson1_lifecycle.demo5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.laioffer.R

/**
 * 类的描述: 版本5: 接口监听法
 * Created by 春夏秋冬在中南 on 2023/9/13 02:22
 */
class Demo5Activity : AppCompatActivity() {

  private var myPresenter: IPresenter? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.empty_layout)

    myPresenter = MyPresenter() // 用户端只需要面向接口编程，想用哪个实现类，直接new那个实现类
    lifecycle.addObserver(myPresenter!!)
  }
}