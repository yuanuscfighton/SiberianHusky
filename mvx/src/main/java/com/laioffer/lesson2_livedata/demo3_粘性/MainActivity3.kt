package com.laioffer.lesson2_livedata.demo3_粘性

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.laioffer.mvx.R

/**
 * 类的描述: LiveData粘性事件
 * Created by 春夏秋冬在中南 on 2023/9/17 10:56
 */
class MainActivity3 : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.single_button_layout)


    val button = findViewById<Button>(R.id.button)
    button.setOnClickListener {

      // 先修改数据，再跳转到MainActivity4
      // version++  == 0   第1步
      MyLiveData.value1.value = "我就是我，不一样的烟火1" // 以前的旧数据
      MyLiveData.value1.value = "我就是我，不一样的烟火2" // 以前的旧数据
      MyLiveData.value1.value = "我就是我，不一样的烟火3" // 以前的旧数据
      MyLiveData.value1.value = "我就是我，不一样的烟火4" // 以前的旧数据
      MyLiveData.value1.value = "我就是我，不一样的烟火5" // 以前的旧数据

      startActivity(Intent(this, MainActivity4::class.java))
    }
  }
}