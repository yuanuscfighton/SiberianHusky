package com.laioffer.lesson2_livedata.demo2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.laioffer.mvx.R

class Demo2Activity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.single_button_layout)

    // 启动服务
    val button = findViewById<Button>(R.id.button)
    button.setOnClickListener {
      startService(Intent(this, MyService::class.java))
      Toast.makeText(this, "推送服务器启动成功", Toast.LENGTH_SHORT).show()
    }

    // 一股脑执行，它不会去检测生命周期
    // sendMessage(handler)


    // 眼睛 观察者  微信列表可见的情况下，才能做事情
    MyLiveData.data1.observe(this) {
      Log.e("server", "界面可见，说明用户在查看微信列表界面啦，更新消息列表UI界面:${it}")
      Toast.makeText(this, "更新消息列表UI界面成功:${it}", Toast.LENGTH_SHORT).show()
    }
  }
}