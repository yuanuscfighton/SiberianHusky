package com.laioffer.lesson3_do_on_next.use1;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {

  // 把Retrofit给Build出来  Retrofit给创建出来
  public static Retrofit createRetrofit() {

    OkHttpClient.Builder builder = new OkHttpClient.Builder();

    builder.readTimeout(10, TimeUnit.SECONDS);
    builder.connectTimeout(9, TimeUnit.SECONDS);

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    builder.addInterceptor(interceptor);


    return new Retrofit.Builder().baseUrl("http://xxxxxxx")
        .client(builder.build())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build();
  }

}
