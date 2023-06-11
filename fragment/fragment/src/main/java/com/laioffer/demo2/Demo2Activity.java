package com.laioffer.demo2;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.laioffer.demo1.Fragment1;
import com.laioffer.demo1.Fragment2;
import com.laioffer.fragment.fragment.R;

/**
 * 类的描述: demo2:动态添加fragment
 * Created by 春夏秋冬在中南 on 2023/6/10 02:25
 */
public class Demo2Activity extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_demo2);

    Button btn1 = findViewById(R.id.btn_show_fragment1);
    btn1.setOnClickListener(v -> {
      FragmentManager fm = getSupportFragmentManager();
      FragmentTransaction ft = fm.beginTransaction();
      Fragment1 fragment1 = new Fragment1();
      ft.add(R.id.demo2_fragment_container, fragment1);
      ft.commit();
    });

    Button btn2 = findViewById(R.id.btn_show_fragment2);
    btn2.setOnClickListener(v -> {
      FragmentManager fm = getSupportFragmentManager();
      FragmentTransaction ft = fm.beginTransaction();
      Fragment2 fragment2 = new Fragment2();
      ft.add(R.id.demo2_fragment_container, fragment2);
      ft.commit();
    });
  }
}
