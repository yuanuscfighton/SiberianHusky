package com.laioffer.okhttp.use1;

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
 * 类的描述: okhttp使用2: POST请求
 * Created by 春夏秋冬在中南 on 2023/8/10 01:57
 */
public class Demo2Post extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }


  /**
   * OkHttp POST请求
   */
  private void requestPOST() throws IOException {
    // 1.创建OKHttpClient对象
    OkHttpClient client = new OkHttpClient();

    // 2.构建FormBody，传入参数
    RequestBody requestBody = new FormBody.Builder()
        .add("name", "张三")
        .build();

    // 3.构建Request，将FormBody作为POST方法的参数传入
    Request request = new Request.Builder()
        .url("xxx")
        .post(requestBody)
        .build();

    // 4.将Request封装为Call
    Call call = client.newCall(request);
    Response response = call.execute();
    ResponseBody body = response.body();
    System.out.println("接口Response=" + body);
  }
}
