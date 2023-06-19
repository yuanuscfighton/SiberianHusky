package com.laioffer.tools;

import android.util.Log;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * 类的描述: Rx工具类
 * Created by 春夏秋冬在中南 on 2023/6/20 00:06
 */
public class RxUtils {

  private static final String TAG = RxUtils.class.getSimpleName();

  /**
   * 封装线程调度的操作
   * U: 表示 upStream 上游
   * D: 表示 downStream 下游
   */
  public static <UD> ObservableTransformer<UD, UD> rxud() {
    return new ObservableTransformer<UD, UD>() {
      @Override
      public ObservableSource<UD> apply(Observable<UD> upstream) {
        return upstream
            .subscribeOn(Schedulers.io()) // 给上面代码分配异步线程
            .observeOn(AndroidSchedulers.mainThread()) // 给下面代码分配主线程;
            .map(new Function<UD, UD>() {
              @Override
              public UD apply(UD ud) {
                Log.d(TAG, "apply: 我监听到你了，居然再执行");
                return ud;
              }
            });
        // .....
      }
    };
  }
}
