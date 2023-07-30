package com.laioffer.lesson5_hook;

import static com.laioffer.tools.Constants.TAG5;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.laioffer.rx.R;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.operators.observable.ObservableMap;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/**
 * 类的描述: RxJava Hook点
 * Created by 春夏秋冬在中南 on 2023/7/30 18:58
 */
public class Activity5 extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.empty_layout);

    RxJavaPlugins.setOnObservableAssembly(new Function<Observable, Observable>() {
      @Override
      public Observable apply(Observable observable) {

        Log.e(TAG5, "设置hook. 监听整个项目有多少个地方使用RxJava: " + observable);

        if (observable instanceof ObservableMap) {
          // return null; ← 导致程序crash
        }
        return observable;
      }
    });

    testHook();
  }

  /**
   * @noinspection ResultOfMethodCallIgnored
   */
  @SuppressLint("CheckResult")
  private void testHook() {
    Observable.create(new ObservableOnSubscribe<String>() {
          @Override
          public void subscribe(@NonNull ObservableEmitter<String> emitter) {
            emitter.onNext("A");
          }
        })
        .map(new Function<String, Boolean>() {
          @Override
          public Boolean apply(String s) {
            return true;
          }
        })
        .subscribe(new Consumer<Boolean>() {
          @Override
          public void accept(Boolean aBoolean) {

          }
        });
  }
}
