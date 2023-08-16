package com.laioffer.okhttp.use;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 类的描述: okhttp使用1: GET请求
 * Created by 春夏秋冬在中南 on 2023/6/18 16:06
 */
public class Demo1Get extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  /**
   * OkHttp GET请求
   */
  private void requestGetSync() throws IOException {
    // 1.创建OKHttpClient对象
    OkHttpClient client = new OkHttpClient();
    // 2.构建Request对象
    Request request = new Request.Builder()
        .url("xxx")
        .build();
    // 3.将Request封装为Call
    Call call = client.newCall(request);
    // 4.同步调用，返回Response，会抛出IO异常
    // 调用execute()后，代码会阻塞在这里，直到服务器有了响应之后，才能继续往下走，得到response
    Response response = call.execute();

    ResponseBody body = response.body();
    System.out.println("接口Response=" + body);
  }

  private void requestGetAsync() {
    // 1.创建OKHttpClient对象
    OkHttpClient client = new OkHttpClient();

    // 2.构建Request对象
    // 注:可以想请求传递查询参数 ← 参考: https://juejin.cn/post/7068162792154464264
    HttpUrl queryParams = HttpUrl.get("xxxx").newBuilder()
        .addQueryParameter("key1", "value1")
        .build();

    Request request = new Request.Builder()
        .url(queryParams)
        .build();

    // 3.将Request封装为Call
    Call call = client.newCall(request);

    // 4.异步调用，设置回调函数
    call.enqueue(new Callback() {
      @Override
      public void onFailure(@NonNull Call call, @NonNull IOException e) {
        System.out.println("onFailure");
      }

      @Override
      public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
        String res = response.body().string();
        System.out.println("onResponse");
      }
    });
  }

}
