package com.laioffer.okhttp.use1;

import static com.laioffer.Constants.BASE_URL;
import static com.laioffer.Constants.KEJI;
import static com.laioffer.IgnoreConstants.API_KEY;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.laioffer.network.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 类的描述: okhttp使用2: POST请求
 * <p>
 * Created by 春夏秋冬在中南 on 2023/8/10 01:57
 */
public class PostRequestClient extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(com.laioffer.network.R.layout.activity_main);
    findViewById(R.id.network_btn_1).setOnClickListener(v -> {
      postRequest();
    });
  }


  /**
   * OkHttp POST请求
   */
  private void postRequest() {
    // 1.创建OKHttpClient对象，类似我们要有一个浏览器
    OkHttpClient client = new OkHttpClient();

    // 2.构建请求体，传入参数
    RequestBody requestBody = new FormBody.Builder()
        .add("key", API_KEY)
        .add("num", "5")
        .build();

    // 3.构建Request对象，将FormBody作为POST方法的参数传入
    Request request = new Request.Builder()
        .url(BASE_URL + KEJI)
        .post(requestBody)
        .build();

    // 4.用client去创建请求任务
    Call task = client.newCall(request);
    task.enqueue(new Callback() {
      @Override
      public void onFailure(@NonNull Call call, @NonNull IOException e) {
        System.out.println("[postRequest] onFailure. e: " + e);
      }

      @Override
      public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
        int code = response.code();
        if (response.body() != null) {
          String res = response.body().string();
          System.out.println("[postRequest] onResponse. code=" + code + ",,, res=" + res);
        }
      }
    });
  }
}
