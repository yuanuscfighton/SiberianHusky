package com.laioffer.material.l1_toolbar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.laioffer.ui.basic.R

/**
 * toolbar
 */
class ToolbarActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.toolbar_activity)
    val toolbar = findViewById<Toolbar>(R.id.toolbar_activity_toolbar)
    setSupportActionBar(toolbar)
  }
}