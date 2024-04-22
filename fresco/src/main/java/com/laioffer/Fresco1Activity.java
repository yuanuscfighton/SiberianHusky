package com.laioffer;

import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.laioffer.app.R;

/**
 * 带进度条的图片
 */
public class Fresco1Activity extends AppCompatActivity {

  private final static String ICON_URL =
      "https://th.bing.com/th/id/R.6b5df1bfe0e4778a44dba0753cd169c8?rik=QRQIMqvjWRCO5Q&riu=http%3a%2f%2fpic39.nipic.com%2f20140321%2f8857347_232251363165_2" +
          ".jpg&ehk=7oAaMo6LCHJc%2bqpQ0IPvcH7v69jGRQhb2vDz%2fOd5720%3d&risl=&pid=ImgRaw&r=0";

  private SimpleDraweeView mIconIv;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fresco_1);

    mIconIv = findViewById(R.id.iv_fresco_1_icon_1);
    initData();
  }

  private void initData() {
    GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
    GenericDraweeHierarchy hierarchy = builder.setProgressBarImage(new ProgressBarDrawable()).build();
    mIconIv.setHierarchy(hierarchy);
    Uri uri = Uri.parse(ICON_URL);
    mIconIv.setImageURI(uri);
  }
}








































/*
使用步骤
第1步：添加依赖
    // fresco
    implementation 'com.facebook.fresco:fresco:2.4.0'
    // 在 API < 14 上的机器支持 WebP 时，需要添加
    implementation 'com.facebook.fresco:animated-base-support:0.12.0'

    // 支持 GIF 动图，需要添加
    implementation 'com.facebook.fresco:animated-gif:2.4.0'

    // 支持 WebP （静态图+动图），需要添加
    implementation 'com.facebook.fresco:animated-webp:2.4.1'
    implementation 'com.facebook.fresco:webpsupport:2.4.1'

    // 仅支持 WebP 静态图，需要添加
    implementation 'com.facebook.fresco:webpsupport:2.4.1'

第2步：在 Application 中初始化 Fresco
    Fresco.initialize(this);

第3步：配置网络权限
    <uses-permission android:name="android.permission.INTERNET"/>

第4步：在 xml 布局文件中，加入命名空间

第5步：在 xml 中引入 SimpleDraweeView

第6步：在 Java 代码中加载图片
 */