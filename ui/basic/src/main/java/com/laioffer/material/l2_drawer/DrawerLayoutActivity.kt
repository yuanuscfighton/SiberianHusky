package com.laioffer.material.l2_drawer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.laioffer.ui.basic.R

/**
 * DrawerLayout
 */
class DrawerLayoutActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_drawerlayout)
    val toolbar = findViewById<Toolbar>(R.id.toolbar_activity_toolbar)
    setSupportActionBar(toolbar)

  }

}