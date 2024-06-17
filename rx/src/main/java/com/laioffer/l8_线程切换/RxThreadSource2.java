package com.laioffer.l8_线程切换;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * 类描述: 线程切换
 * <p>
 * created by 西伯利亚哈士奇 on 2024/6/4 23:08
 */
public class RxThreadSource2 extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    new Thread(this::test).start();
  }

  private void test() {
    Observable.create(new ObservableOnSubscribe<String>() {
          @Override
          public void subscribe(@NonNull ObservableEmitter<String> emitter) {
            emitter.onNext("线程切换");
          }
        })
        .observeOn(AndroidSchedulers.mainThread()) // 主线程 handler
        .subscribe(
            new Observer<String>() {
              @Override
              public void onSubscribe(@NonNull Disposable d) {
                System.out.println("[onSubscribe]: " + Thread.currentThread().getName());
              }

              @Override
              public void onNext(@NonNull String s) {
                System.out.println("[onNext]: " + Thread.currentThread().getName());
              }

              @Override
              public void onError(@NonNull Throwable e) {
                System.out.println("[onError]: " + Thread.currentThread().getName());
              }

              @Override
              public void onComplete() {
                System.out.println("[onComplete]: " + Thread.currentThread().getName());
              }
            });
  }
}
