package com.laioffer.youtube.demo2_构造器注入依赖;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.laioffer.R;

/**
 * 类的描述:
 * Created by 春夏秋冬在中南 on 2023/8/13 13:33
 */
public class Demo2Activity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.empty_layout);

    Engine engine = new Engine();
    Wheels wheels = new Wheels();

    Car car = new Car(engine, wheels);
    car.drive();
  }
}
