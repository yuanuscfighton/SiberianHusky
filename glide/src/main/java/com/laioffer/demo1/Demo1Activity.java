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
 * <p>
 * Created by 春夏秋冬在中南 on 2023/6/6 23:27
 */
public class Demo1Activity extends AppCompatActivity {

  Button btn;
  ImageView img;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    btn = findViewById(R.id.btn_load);
    img = findViewById(R.id.img);

    btn.setOnClickListener(v -> loadPic());

    // 子线程
    new Thread(() -> Glide.with(this.getApplicationContext()).load("xxxx").into(img)).start();
  }

  /*
     Glide自动监听onDestroy，自动回收♻️
   */
  @Override
  protected void onDestroy() {
    super.onDestroy();
    Glide.with(this).clear(img);
  }

  private void loadPic() {
    String url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
    Glide.with(this).load(url).into(img);
  }
}
