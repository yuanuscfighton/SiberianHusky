package com.laioffer.retrofit.use1;

import static com.laioffer.Constants.BASE_URL;
import static com.laioffer.IgnoreConstants.API_KEY;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.laioffer.network.R;
import com.laioffer.retrofit.ApiService;
import com.laioffer.retrofit.NewsData;

import java.net.HttpURLConnection;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 类的描述: Retrofit发起GET请求
 * <p>
 * Created by 春夏秋冬在中南 on 2023/9/22 01:36
 */
public class RetrofitGetClient extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.empty_layout);

    getRequest();
  }


  private void getRequest() {
    // 第4步：构建一个Retrofit实例
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .build();

    // 第3步：
    // （1）创建 网络请求接口的实例
    ApiService api = retrofit.create(ApiService.class);
    // （2）对发送请求进行封装
    Call<NewsData> task = api.getNewsData(API_KEY, 5);

    // 第5步：发送网络请求（异步/同步）
    task.enqueue(new Callback<NewsData>() {
      @Override
      public void onResponse(@NonNull Call<NewsData> call, @NonNull Response<NewsData> response) {
        // 第6步：处理返回的数据
        if (response.code() == HttpURLConnection.HTTP_OK) {
          if (response.body() != null) {
            // response.body().toString() 拿到的是Json字符串，希望是转成Bean类
            System.out.println("Retrofit发起GET请求. response: " + response.body());

            // 使用 Gson 将 Json 字符串转成对象
            String result = response.body().toString();
            Gson gson = new Gson();
            NewsData data = gson.fromJson(result, NewsData.class);
            System.out.println("数据对象是 " + data);
          }
        }
      }

      @Override
      public void onFailure(@NonNull Call<NewsData> call, @NonNull Throwable t) {

      }
    });
  }


  /**
   * 使用「转换器」可以将 response.body() 返回的 Json 字符串自动转成对象
   */
  private void getRequest2() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(new Gson())) // 设置数据解析器
        .build();

    ApiService apiService = retrofit.create(ApiService.class);
    Call<NewsData> call = apiService.getNewsData(API_KEY, 10);

    call.enqueue(new Callback<NewsData>() {
      @Override
      public void onResponse(@NonNull Call<NewsData> call, @NonNull Response<NewsData> response) {
        NewsData newsData = response.body();
        System.out.println("[使用转换器将Json字符串转成对象] 转换后的结果是: " + newsData);
      }

      @Override
      public void onFailure(@NonNull Call<NewsData> call, @NonNull Throwable t) {

      }
    });
  }


  private void getRequest3() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(new Gson()))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build();

    ApiService apiService = retrofit.create(ApiService.class);

    Observable<NewsData> observable = apiService.getNewsDataObservable(API_KEY, 10);
    observable.subscribe(
        new Consumer<NewsData>() {
          @Override
          public void accept(NewsData newsData) {

          }
        },
        new Consumer<Throwable>() {
          @Override
          public void accept(Throwable throwable) {

          }
        }
    );

  }

}
