package com.laioffer.l8_线程切换;

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
public class RxThreadSource1 {

  public static void main(String[] args) {
    Observable.create(new ObservableOnSubscribe<String>() {
          @Override
          public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
            emitter.onNext("线程切换");
          }
        })
        .subscribeOn(Schedulers.io())
        .subscribe(
            new Observer<String>() {
              @Override
              public void onSubscribe(@NonNull Disposable d) {

              }

              @Override
              public void onNext(@NonNull String s) {

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
