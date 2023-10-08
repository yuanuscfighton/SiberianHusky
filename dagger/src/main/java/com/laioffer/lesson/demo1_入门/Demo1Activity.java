package com.laioffer.lesson.demo1_入门;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.laioffer.lesson.DevTools;

import javax.inject.Inject;

public class Demo1Activity extends AppCompatActivity {

  @Inject
  Computer mComputer;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // 把收货地址告诉快递员
    DaggerComputerComponent.create().injectMainActivity(this);
    DevTools.logcatMessage("computer" + mComputer);
  }
}
