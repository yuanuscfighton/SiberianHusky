package com.laioffer.youtube.v3_constructor_inject;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.laioffer.R;

public class Car3Activity extends AppCompatActivity {

  private Car mCar;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.empty_layout);

    // We want Dagger to initiate Car object,
    // so we need to create a class called CarComponent, from where we will get car object.
    // XxxComponent是一个接口，我们可以拿到Car的实例.

    // CarComponent component = DaggerCarComponent.create();
    // mCar = component.getCar();
    mCar.drive();
  }
}
