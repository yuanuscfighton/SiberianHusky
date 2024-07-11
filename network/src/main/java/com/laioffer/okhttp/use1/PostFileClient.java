package com.laioffer.okhttp.use1;

import static com.laioffer.Constants.BASE_URL;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.laioffer.network.R;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 类描述: 上传文件
 * <p>
 * created by 西伯利亚哈士奇 on 2024/6/23 17:08
 */
public class PostFileClient extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(com.laioffer.network.R.layout.activity_main);
    findViewById(R.id.network_btn_1).setOnClickListener(v -> {
      postFile();
    });
  }

  /**
   * 单文件上传
   */
  private void postFile() {
    OkHttpClient client = new OkHttpClient.Builder()
        .connectTimeout(10_000, TimeUnit.MILLISECONDS)
        .build();

    File file = new File("xxxx");
    MediaType mediaType = MediaType.parse("image/png");
    RequestBody fileBody = RequestBody.create(file, mediaType);
    RequestBody requestBody = new MultipartBody.Builder()
        .addFormDataPart("file", file.getName(), fileBody)
        .build();

    Request request = new Request.Builder()
        .url(BASE_URL + "/file/upload")
        .post(requestBody)
        .build();

    Call task = client.newCall(request);
    task.enqueue(new Callback() {
      @Override
      public void onFailure(@NonNull Call call, @NonNull IOException e) {

      }

      @Override
      public void onResponse(@NonNull Call call, @NonNull Response response) {

      }
    });
  }

  /**
   * 多文件上传
   */
  private void postFiles() {
    OkHttpClient client = new OkHttpClient.Builder()
        .connectTimeout(10_000, TimeUnit.MILLISECONDS)
        .build();

    File file1 = new File("xxx1");
    File file2 = new File("xxx2");
    File file3 = new File("xxx3");
    File file4 = new File("xxx4");

    MediaType mediaType = MediaType.parse("image/png");

    RequestBody file1Body = RequestBody.create(file1, mediaType);
    RequestBody file2Body = RequestBody.create(file2, mediaType);
    RequestBody file3Body = RequestBody.create(file3, mediaType);
    RequestBody file4Body = RequestBody.create(file4, mediaType);
    RequestBody requestBody = new MultipartBody.Builder()
        .addFormDataPart("files", file1.getName(), file1Body)
        .addFormDataPart("files", file2.getName(), file2Body)
        .addFormDataPart("files", file3.getName(), file3Body)
        .addFormDataPart("files", file4.getName(), file4Body)
        .build();

    Request request = new Request.Builder()
        .url(BASE_URL + "/file/upload")
        .post(requestBody)
        .build();

    Call task = client.newCall(request);
    task.enqueue(new Callback() {
      @Override
      public void onFailure(@NonNull Call call, @NonNull IOException e) {

      }

      @Override
      public void onResponse(@NonNull Call call, @NonNull Response response) {

      }
    });
  }
}
