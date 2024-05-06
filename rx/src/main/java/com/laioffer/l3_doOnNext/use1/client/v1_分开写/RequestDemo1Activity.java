package com.laioffer.l3_doOnNext.use1.client.v1_分开写;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.laioffer.l3_doOnNext.use1.MyRetrofit;
import com.laioffer.l3_doOnNext.use1.api.IRequestNetwork;
import com.laioffer.l3_doOnNext.use1.api.LoginRequest;
import com.laioffer.l3_doOnNext.use1.api.RegisterRequest;
import com.laioffer.l3_doOnNext.use1.bean.LoginResponse;
import com.laioffer.l3_doOnNext.use1.bean.RegisterResponse;
import com.laioffer.rx.R;
import com.laioffer.tools.RxUtils;

import io.reactivex.rxjava3.functions.Consumer;

/**
 * 类的描述: 频繁的线程切换
 * Created by 春夏秋冬在中南 on 2023/7/30 09:53
 */

/*
具体内容: <-- 频繁切换线程
    (1)请求服务器注册操作 --> 耗时操作
    (2)注册完成之后，更新注册UI
    (3)马上去登录服务器操作 --> 耗时操作
    (4)登录完成之后，更新登录的UI

RxJava配合Retrofit (请求网络OkHttp -- Retrofit -- Observable)
    1.OkHttp 请求网络(Retrofit)
    2.Retrofit 返回一个结果 （Retrofit） --- Observable
    3.最终的结果 是RxJava中的 被观察者 上游 Observable
    4.一行代码写完需求流程： 从上往下
        (1)请求服务器，执行注册操作（耗时）切换异步线程
        (2)更新注册后的所有 注册相关UI - main 切换主线程
        (3)请求服务器，执行登录操作（耗时）切换异步线程
        (4)更新登录后的所有 登录相关UI - main 切换主线程
    5.看RxJava另外一种的执行流程
      初始点 开始点 订阅
        (1)onSubscribe
        (2)registerAction(new RegisterRequest())
        (3)doOnNext 更新注册后的 所有UI
        (4)flatMap执行登录的耗时操作
        (5)订阅的观察者 下游 onNext 方法，更新所有登录后的UI
        (6)progressDialog.dismiss()
 */
public class RequestDemo1Activity extends AppCompatActivity {

  private final String TAG = RequestDemo1Activity.class.getSimpleName();

  private TextView tv_register_ui;
  private TextView tv_login_ui;

  @SuppressLint("CutPasteId")
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout31);

    tv_register_ui = findViewById(R.id.tv_login_ui);
    tv_login_ui = findViewById(R.id.tv_login_ui);

  }

  // 方式1: 分开写
  @SuppressLint("CheckResult")
  @SuppressWarnings("ResultOfMethodCallIgnored")
  public void request(View view) {
    // 1.请求服务器注册操作
    MyRetrofit.createRetrofit().create(IRequestNetwork.class)
        // 耗时操作，分配子线程
        .registerAction(new RegisterRequest())
        .compose(RxUtils.rxud())
        .subscribe(new Consumer<RegisterResponse>() {
          @Override
          public void accept(RegisterResponse registerResponse) {
            // 2.注册完成之后，更新注册UI
            // .....
          }
        });

    // 3.马上去登录服务器操作
    MyRetrofit.createRetrofit().create(IRequestNetwork.class)
        .loginAction(new LoginRequest())
        .compose(RxUtils.rxud())
        .subscribe(new Consumer<LoginResponse>() {
          @Override
          public void accept(LoginResponse loginResponse) {
            // 4.登录完成之后，更新登录的UI
            // .....
          }
        });
  }

}
