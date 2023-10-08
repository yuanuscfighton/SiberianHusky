package com.laioffer.lesson4_viewmodel.use1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.laioffer.R

class Demo1Activity : AppCompatActivity() {

  private lateinit var myViewModel: MyViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.text_and_button_layout)

    myViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
      .get(MyViewModel::class.java)

    val tv = findViewById<TextView>(R.id.tv)
    val btn = findViewById<Button>(R.id.plus_1_btn)

    tv.text = "${myViewModel.number}"

    btn.setOnClickListener {
      tv.text = "${++myViewModel.number}"
    }
  }
}