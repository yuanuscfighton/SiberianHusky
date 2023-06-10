package com.laioffer

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.laioffer.demo1.Demo1Activity
import com.laioffer.demo2.Demo2Activity
import com.laioffer.demo3.Demo3Activity
import com.laioffer.fragment.fragment.R

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    startNewActivity(R.id.demo1_activity, "Fragment基本使用", Demo1Activity::class.java)
    startNewActivity(R.id.demo2_activity, "动态添加Fragment", Demo2Activity::class.java)
    startNewActivity(R.id.demo3_activity, "管理Fragment", Demo3Activity::class.java)
  }

  private fun startNewActivity(btnId: Int, name: String, clazz: Class<*>) {
    val tv = findViewById<TextView>(btnId)
    tv.text = name
    tv.setOnClickListener {
      val intent = Intent(this@MainActivity, clazz)
      startActivity(intent)
    }
  }
}