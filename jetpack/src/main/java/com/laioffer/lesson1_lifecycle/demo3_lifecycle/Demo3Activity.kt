package com.laioffer.lesson1_lifecycle.demo3_lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.laioffer.R

/**
 * 类描述: 使用LifecycleOwner和LifecycleObserver实现
 *
 * created by 春夏秋冬在中南 on 2023/11/29 08:09
 */
class Demo3Activity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.empty_layout)

    /* 2.这里我们只需要创建一个观察者，盯着看就行 */
    lifecycle.addObserver(MyObserver())
    lifecycle.addObserver(MyObserver1())
  }

  /* 1.由于 AppCompatActivity 实现了LifecycleOwner接口，我们就不需要再写👇🏻的代码了 */
  // override fun onResume() {
  //   super.onResume()
  //   mMyPresenter?.onResume()
  // }
}