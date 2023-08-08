package com.laioffer.lesson7_map操作符;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.laioffer.rx.R;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;

/**
 * 类的描述: map操作符源码分析
 * Created by 春夏秋冬在中南 on 2023/8/6 15:04
 */
public class Demo7 extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.empty_layout);

    // Observable.create()返回ObservableCreate对象
    Observable.create(
            // 自定义source
            new ObservableOnSubscribe<String>() {
              @Override
              public void subscribe(@NonNull ObservableEmitter<String> emitter) {

              }
            })

        // 相当于是ObservableCreate.map()
        .map(new Function<String, Bitmap>() {
          @Override
          public Bitmap apply(String s) {
            return null;
          }
        })

        // 相当于是ObservableMap.subscribe()
        .subscribe(
            // 自定义观察者(终点)
            new Observer<Bitmap>() {
              @Override
              public void onSubscribe(@NonNull Disposable d) {

              }

              @Override
              public void onNext(@NonNull Bitmap bitmap) {

              }

              @Override
              public void onError(@NonNull Throwable e) {

              }

              @Override
              public void onComplete() {

              }
            });
  }
}
