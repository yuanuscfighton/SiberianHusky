package com.laioffer.lesson.手写dagger;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.laioffer.R;

import javax.inject.Inject;


/**
 * @description 注入目标
 * @date 2022/10/6 3:19 下午
 */
public class MainActivity extends AppCompatActivity {

  @Inject // 第5个注解
  public Computer student;

  @Inject // 第5个注解
  public Computer student2;

  @Override
  protected void onCreate(Bundle savedInstanceState) { // 方法进栈
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // 第4个注解@Component生成的类
    DaggerComputerComponent.create().inject(this);

    Toast.makeText(this, "student:" + student.hashCode(), Toast.LENGTH_SHORT).show();

    Toast.makeText(this, "student:" + student2.hashCode(), Toast.LENGTH_SHORT).show();
  }
}
