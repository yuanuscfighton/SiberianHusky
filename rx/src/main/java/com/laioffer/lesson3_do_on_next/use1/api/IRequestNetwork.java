package com.laioffer.lesson3_do_on_next.use1.api;

import com.laioffer.lesson3_do_on_next.use1.bean.LoginResponse;
import com.laioffer.lesson3_do_on_next.use1.bean.RegisterResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;

// 请求接口 API
public interface IRequestNetwork {

  // 请求注册 功能  耗时操作 ---> OkHttp
  Observable<RegisterResponse> registerAction(@Body RegisterRequest registerRequest);

  // 请求登录 功能 耗时操作 ---> OKHttp
  Observable<LoginResponse> loginAction(@Body LoginRequest loginRequest);

}
