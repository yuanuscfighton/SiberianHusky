package com.laioffer.okhttp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 类的描述: okhttp使用
 * Created by 春夏秋冬在中南 on 2023/6/18 16:06
 */
public class Demo1Activity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  /**
   * OkHttp GET请求
   */
  private void requestGET() throws IOException {
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
        .url("xxx")
        .build();
    Call call = client.newCall(request);
    // 执行同步请求
    // 调用execute()后，代码会阻塞在这里，直到服务器有了响应之后，才能继续往下走，得到response
    Response response = call.execute();

    ResponseBody body = response.body();
    System.out.println("接口Response=" + body);
  }

  /**
   * OkHttp POST请求
   */
  private void requestPOST() throws IOException {
    OkHttpClient client = new OkHttpClient();
    RequestBody requestBody = new FormBody.Builder()
        .add("name", "张三")
        .build();
    Request request = new Request.Builder()
        .url("xxx")
        .post(requestBody)
        .build();
    Call call = client.newCall(request);
    Response response = call.execute();
    ResponseBody body = response.body();
    System.out.println("接口Response=" + body);
  }
}
