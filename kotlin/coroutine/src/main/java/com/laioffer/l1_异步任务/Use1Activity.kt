package com.laioffer.l1_异步任务

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.laioffer.basic.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Use1Activity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_use1)

    val tv1: TextView = findViewById(R.id.activity_use1_tv1)

    findViewById<Button>(R.id.activity_use1_button).also {
      it.setOnClickListener {
        GlobalScope.launch(Dispatchers.Main) {
          var hotWordResponse: HotWordResponse?
          withContext(Dispatchers.IO) {
            hotWordResponse = userServiceApi.getHotWordData()
          }

          tv1.text = hotWordResponse?.toString()
        }
      }
    }
  }
}