package com.laioffer.lesson2_livedata.demo1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.laioffer.R
import kotlin.concurrent.thread

/**
 * 类的描述: LiveData简单使用
 *
 * Created by 春夏秋冬在中南 on 2023/9/16 21:10
 */
class Demo1Activity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.single_tv_layout)

    val textView: TextView = findViewById(R.id.tv_textview)

    // 1 观察者 眼睛 环节
    MyLiveData.info1.observe(this) {
      textView.text = it // 更新UI
    }

    // 完整写法 new  Observer  onChanged
    // MyLiveData.info1.observe(this, object : Observer<String> {
    //   override fun onChanged(t: String?) {
    //     textView.text = t // 更新UI
    //   }
    // })


    // 2 触发数据改变 环节
    MyLiveData.info1.value = "default" // setValue 主线程

    thread {
      Thread.sleep(3000)
      MyLiveData.info1.postValue("3秒钟后，修改了哦")  // postValue 子线程
    }

    thread { // 子线程
      Thread.sleep(6000)
      MyLiveData.info1.postValue("6秒钟后，修改了哦")  // postValue 子线程
    }
  }

}