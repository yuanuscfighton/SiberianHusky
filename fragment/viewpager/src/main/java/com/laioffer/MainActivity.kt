package com.laioffer

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.laioffer.demo1.Demo1Activity
import com.laioffer.fragment.viewpager.R
import com.laioffer.viewpage2.demo1.Vp2Demo1Activity


class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    startNewActivity(R.id.viewpager_demo1_btn, Demo1Activity::class.java)
    startNewActivity(R.id.viewpager2_demo1_btn, Vp2Demo1Activity::class.java)
  }

  private fun startNewActivity(buttonRes: Int, clazz: Class<*>) {
    findViewById<View>(buttonRes).setOnClickListener {
      val intent = Intent(this, clazz)
      startActivity(intent)
    }
  }
}