package com.laioffer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.laioffer.material.l1_toolbar.ToolbarActivity
import com.laioffer.material.l2_drawer.DrawerLayoutActivity
import com.laioffer.ui.basic.R

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    startNewActivity(R.id.material_toolbar_button, ToolbarActivity::class.java)
    startNewActivity(R.id.material_drawerlayout_button, DrawerLayoutActivity::class.java)

  }

  private fun startNewActivity(buttonId: Int, clazz: Class<*>) {
    val button = findViewById<Button>(buttonId)
    button.setOnClickListener {
      startActivity(Intent(this@MainActivity, clazz))
    }
  }
}