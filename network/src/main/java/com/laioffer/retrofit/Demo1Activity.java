package com.laioffer.retrofit;

import static com.laioffer.IgnoreConstants.API_KEY;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.laioffer.network.R;

import io.reactivex.rxjava3.functions.Consumer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 类的描述:
 * Created by 春夏秋冬在中南 on 2023/9/22 01:36
 */
public class Demo1Activity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.empty_layout);

    request();
  }


  private void request() {
    // 第4步：构建一个Retrofit实例
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://apis.tianapi.com")
        .addConverterFactory(GsonConverterFactory.create(new Gson())) // 设置数据解析器
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // 支持RxJava
        .build();

    // 第5步：
    // (1) 创建 网络请求接口的实例
    ApiService apiService = retrofit.create(ApiService.class);

    // (2) 对发送请求进行封装
    Call<NewsData> call = apiService.getNewsData(API_KEY, 10);

    // 第6步：发送网络请求（异步/同步）
    call.enqueue(new Callback<NewsData>() {
      @Override
      public void onResponse(@NonNull Call<NewsData> call, @NonNull Response<NewsData> response) {
        // 第7步：处理返回的数据
        NewsData newsData = response.body();
      }

      @Override
      public void onFailure(@NonNull Call<NewsData> call, @NonNull Throwable t) {

      }
    });
  }


  @SuppressLint("CheckResult")
  private void request1() {
    // 第4步：构建一个Retrofit实例
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://apis.tianapi.com")
        .addConverterFactory(GsonConverterFactory.create(new Gson())) // 设置数据解析器
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // 支持RxJava
        .build();

    ApiService apiService = retrofit.create(ApiService.class);
    //noinspection ResultOfMethodCallIgnored
    apiService.getNewsDataObservable(API_KEY, 5)
        .subscribe(
            new Consumer<NewsData>() {
              @Override
              public void accept(NewsData newsData) {

              }
            },
            new Consumer<Throwable>() {
              @Override
              public void accept(Throwable throwable) {

              }
            });
  }
}
