package com.laioffer.source;

import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 类描述:
 * <p>
 * created by 西伯利亚哈士奇 on 2024/7/18 22:44
 */
public class Client1Activity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // requestWindowFeature(Window.FEATURE_NO_TITLE);

    // 为什么使用 requestWindowFeature 不生效，而要使用 supportRequestWindowFeature？
    // 因为这里是继承的 AppCompatActivity，supportRequestWindowFeature 内部实现是
    // getDelegate().requestWindowFeature(featureId);
    supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_main);
  }
}
