package com.laioffer.okhttp.use1;

import static com.laioffer.IgnoreConstants.API_KEY;
import static com.laioffer.IgnoreConstants.BASE_URL_1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.laioffer.network.R;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 类的描述: okhttp使用1: GET请求
 * <p>
 * Created by 春夏秋冬在中南 on 2023/6/18 16:06
 */
public class GetRequestActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    findViewById(R.id.network_btn_1).setOnClickListener(v -> {
      getRequestAsync();
    });
  }

  /**
   * OkHttp GET请求
   */
  private void getRequestSync() throws IOException {
    // 1.创建OKHttpClient对象，类似我们要有一个浏览器
    OkHttpClient client = new OkHttpClient.Builder()
        .connectTimeout(10_000, TimeUnit.MILLISECONDS)
        .build();

    // 2.构建Request对象，创建请求内容
    Request request = new Request.Builder()
        .get()
        .url(BASE_URL_1 + "?key=" + API_KEY)
        .build();

    // 3. 用client去创建请求任务
    Call call = client.newCall(request);

    // 4.同步调用，返回Response，会抛出IO异常
    // 调用execute()后，代码会阻塞在这里，直到服务器有了响应之后，才能继续往下走，得到response
    Response response = call.execute();

    ResponseBody body = response.body();
    System.out.println("接口Response=" + body);
  }

  private void getRequestAsync() {
    // 1.创建OKHttpClient对象，类似我们要有一个浏览器
    OkHttpClient client = new OkHttpClient.Builder()
        .connectTimeout(10_000, TimeUnit.MILLISECONDS)
        .build();

    // 2.构建Request对象，创建请求内容
    Request request = new Request.Builder()
        .get()
        .url(BASE_URL_1 + "?key=" + API_KEY)
        .build();

    // 3. 用client去创建请求任务
    Call task = client.newCall(request);

    // 4.异步调用，设置回调函数
    task.enqueue(new Callback() {
      @Override
      public void onFailure(@NonNull Call call, @NonNull IOException e) {
        System.out.println("onFailure");
      }

      @Override
      public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
        int code = response.code();
        if (response.body() != null) {
          String res = response.body().string();
          System.out.println("onResponse. code=" + code + ",,, res=" + res);
        }
      }
    });
  }

}
