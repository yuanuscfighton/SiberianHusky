package com.laioffer.ui.source;


import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;

import com.laioffer.ui.R;

/**
 * 分析 Activity # setContentView
 */
public class Client1 extends Activity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.empty_layout);
  }
}
