package com.laioffer.lesson.demo3_局部单例.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.laioffer.R;
import com.laioffer.lesson.demo3_局部单例.component.DaggerObjComponents;
import com.laioffer.lesson.demo3_局部单例.obj.HttpObject;

import javax.inject.Inject;

/**
 * 类的描述: 局部单例
 * Created by 春夏秋冬在中南 on 2023/8/25 07:50
 */
public class SecondActivity extends AppCompatActivity {

  public static final String TAG = "[demo3_局部单例]" + SecondActivity.class.getSimpleName();

  @Inject
  HttpObject mHttpObject;

  @SuppressLint("SetTextI18n")
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.simple_item_layout);
    TextView itemText = findViewById(R.id.item_text_view);
    itemText.setText("[demo3_局部单例][SecondActivity]");

    DaggerObjComponents.create().injectSecondActivity(this);

    Log.e(TAG, "obj:" + mHttpObject.hashCode()); // 185929891
  }
}
