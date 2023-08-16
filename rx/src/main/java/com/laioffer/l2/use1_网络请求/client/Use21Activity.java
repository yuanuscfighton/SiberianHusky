package com.laioffer.l2.use1_网络请求.client;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.laioffer.l2.use1_网络请求.api.WangAndroidApi;
import com.laioffer.l2.use1_网络请求.bean.ProjectBean;
import com.laioffer.l2.use1_网络请求.util.HttpUtil;
import com.laioffer.rx.R;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * 类的描述: Rx使用场景1: Retrofit+RxJava查询xxx
 * Created by 春夏秋冬在中南 on 2023/7/29 21:31
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class Use21Activity extends AppCompatActivity {

  private final static String TAG = Use21Activity.class.getSimpleName();

  private WangAndroidApi api;
  private Disposable mGetProjectDisposable;
  private Disposable mGetItemDisposable;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout21);

    // 初始化
    api = HttpUtil.getOnlineCookieRetrofit().create(WangAndroidApi.class);

    // 按钮1: 获取项目的整个分类的数据
    findViewById(R.id.l21_use1_button1).setOnClickListener(v -> {
      getAllCategoriesData1();
      // getAllCategoriesData2();
    });

    // 按钮2: 获取某一个分类下项目列表数据
    findViewById(R.id.l21_use1_button2).setOnClickListener(v -> {
      getOneCategoryData();
    });
  }

  /**
   * Retrofit+RxJava 获取项目的整个分类的数据
   * 使用Consumer
   */
  public void getAllCategoriesData1() {
    // 获取网络API
    mGetProjectDisposable = api.getProject()
        .subscribeOn(Schedulers.io()) // 给上面分配异步线程
        .observeOn(AndroidSchedulers.mainThread()) // 给下面分配主线程
        .subscribe(new Consumer<ProjectBean>() { // Consumer简化版本的Observer
          @Override
          public void accept(ProjectBean projectBean) {
            Log.d(TAG, "accept: " + projectBean); // UI 可以做事情
          }
        });
  }

  /**
   * Retrofit+RxJava 获取项目的整个分类的数据
   * 使用Observer
   */
  public void getAllCategoriesData2() {
    // 获取网络API
    api.getProject()
        .subscribeOn(Schedulers.io()) // 给上面分配异步线程
        .observeOn(AndroidSchedulers.mainThread()) // 给下面分配主线程
        .subscribe(new Observer<ProjectBean>() {
          @Override
          public void onSubscribe(@NonNull Disposable d) {

          }

          @Override
          public void onNext(@NonNull ProjectBean projectBean) {

          }

          @Override
          public void onError(@NonNull Throwable e) {

          }

          @Override
          public void onComplete() {

          }
        });
  }

  /**
   * Retrofit+RxJava 获取某一个分类下项目列表的数据
   */
  public void getOneCategoryData() {
    // 注意:这里的 294 是项目分类 所查询出来的数据
    // 上面的项目分类会查询出："id": 294,"id": 402,"id": 367,"id": 323,"id": 314, ...

    // id 写死的
    mGetItemDisposable = api.getProjectItem(1, 294)
        // .....
        .subscribeOn(Schedulers.io()) // 上面 异步
        .observeOn(AndroidSchedulers.mainThread()) // 下面 主线程
        .subscribe(data -> {
          Log.d(TAG, "getProjectListAction: " + data);
        });

  }
}
