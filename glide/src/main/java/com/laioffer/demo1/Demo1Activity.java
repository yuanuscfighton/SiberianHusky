package com.laioffer.demo1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.laioffer.R;

/**
 * 类的描述: Glide最基本使用
 * Created by 春夏秋冬在中南 on 2023/6/6 23:27
 */
public class Demo1Activity extends AppCompatActivity {

  Button btn;
  ImageView img;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    init();
  }

  private void init() {
    btn = (Button)findViewById(R.id.btn_load);

    img = (ImageView)findViewById(R.id.img);

    btn.setOnClickListener(v -> btnLoad());
  }

  private void btnLoad() {
    String url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";

    Glide.with(this).load(url).into(img);
  }
}
