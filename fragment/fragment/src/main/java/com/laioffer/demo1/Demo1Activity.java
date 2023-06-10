package com.laioffer.demo1;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.laioffer.fragment.fragment.R;

/**
 * 类的描述: demo1:Fragment基本使用
 * Created by 春夏秋冬在中南 on 2023/6/8 23:21
 */
public class Demo1Activity extends FragmentActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_demo1);
  }
}
