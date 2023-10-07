package com.laioffer.lesson2_livedata.demo3_粘性

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.laioffer.R

class MainActivity4 : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.empty_layout)

    // 我后观察数据，居然能够收到 前面修改的数据 <== 这就是「数据黏性」
    // 第2步
    MyLiveData.value1.observe(this) { t ->
      Toast.makeText(
        this@MainActivity4,
        "观察者数据变化$t",
        Toast.LENGTH_SHORT
      ).show()
    }

    // 此观察者 和 handler没有区别，一股脑的执行 （极端的情况，可以用）
    // 手动考虑释放工作
    MyLiveData.value1.observeForever {
      // ... ...
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    // 手动释放
    // MyLiveData.value1.removeObserver(xxx)
  }
}