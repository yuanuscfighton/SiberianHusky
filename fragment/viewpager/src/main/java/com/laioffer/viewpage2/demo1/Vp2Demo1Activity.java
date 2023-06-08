package com.laioffer.viewpage2.demo1;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.laioffer.fragment.viewpager.R;

/**
 * 类的描述: ViewPager2
 * Created by 春夏秋冬在中南 on 2023/4/26 00:29
 */

// ViewPager2内部实现原理是使用RecyclerView加LinearLayoutManager实现竖直滚动，可以理解为对Recyclerview的二次封装
public class Vp2Demo1Activity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_vp2_demo1);
    ViewPager2 vp2 = findViewById(R.id.vp2_demo1_vp);
    ViewPagerAdapter adapter = new ViewPagerAdapter();
    // 设置滚动方向
    vp2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
    vp2.setAdapter(adapter);
  }
}
