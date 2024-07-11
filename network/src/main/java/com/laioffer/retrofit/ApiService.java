package com.laioffer.retrofit;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

/**
 * 类的描述: 第1步：创建 用于描述网络请求的接口
 * <p>
 * Created by 春夏秋冬在中南 on 2023/9/22 08:09
 */
public interface ApiService {

  // 国内新闻：https://apis.tianapi.com/guonei/index?key=你的APIKEY&num=10

  /**
   * Retrofit将 http请求抽象成Java接口，使用注解 描述网络请求参数 和配置网络请求参数
   */
  @POST("/guonei/index")
  Call<NewsData> getNewsData(
      @Field("key") String apiKey,
      @Field("num") int number);

  @POST("/guonei/index")
  Observable<NewsData> getNewsDataObservable(
      @Field("key") String apiKey,
      @Field("num") int number);
}
