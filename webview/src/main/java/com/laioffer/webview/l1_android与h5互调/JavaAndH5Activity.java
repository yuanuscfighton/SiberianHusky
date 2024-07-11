package com.laioffer.webview.l1_android与h5互调;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.laioffer.webview.R;

public class JavaAndH5Activity extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.acitivty_java_and_h5);
    initWebView();
  }

  @SuppressLint("SetJavaScriptEnabled")
  private void initWebView() {
    // 加载网页 h5、html，自定义浏览器
    WebView webView = new WebView(this);
    WebSettings webSettings = webView.getSettings();

    // 不调用浏览器
    webView.setWebViewClient(new WebViewClient());

    // 设置支持 JavaScript
    webSettings.setJavaScriptEnabled(true);

    // 加载网络的网页、本地的网页
    webView.loadUrl("https://juejin.cn/");
  }
}
