package com.laioffer;

import android.annotation.SuppressLint;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.laioffer.app.R;

/**
 * 不同的裁剪方式
 */
public class Fresco2Activity extends AppCompatActivity {

  private final static String ICON_URL =
      "https://th.bing.com/th/id/R.6b5df1bfe0e4778a44dba0753cd169c8?rik=QRQIMqvjWRCO5Q&riu=http%3a%2f%2fpic39.nipic.com%2f20140321%2f8857347_232251363165_2" +
          ".jpg&ehk=7oAaMo6LCHJc%2bqpQ0IPvcH7v69jGRQhb2vDz%2fOd5720%3d&risl=&pid=ImgRaw&r=0";

  private SimpleDraweeView mIconIv;
  private TextView mDescTv;

  private Button mCenterBtn;
  private Button mCenterCropBtn;
  private Button mFocusCropBtn;
  private Button mCenterInsideBtn;
  private Button mFitCenterBtn;
  private Button mFitStartBtn;
  private Button mFitEndBtn;
  private Button mFitXYBtn;
  private Button mNoneBtn;

  private GenericDraweeHierarchyBuilder mHierarchyBuilder;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fresco_2);

    mIconIv = findViewById(R.id.iv_fresco_2_icon);
    mDescTv = findViewById(R.id.tv_fresco_2_desc);
    mCenterBtn = findViewById(R.id.btn_fresco_2_center);
    mCenterCropBtn = findViewById(R.id.btn_fresco_2_center_crop);
    mFocusCropBtn = findViewById(R.id.btn_fresco_2_focus_crop);
    mCenterInsideBtn = findViewById(R.id.btn_fresco_2_center_inside);
    mFitCenterBtn = findViewById(R.id.btn_fresco_2_fit_center);
    mFitStartBtn = findViewById(R.id.btn_fresco_2_fit_start);
    mFitEndBtn = findViewById(R.id.btn_fresco_2_fit_end);
    mFitXYBtn = findViewById(R.id.btn_fresco_2_fit_xy);
    mNoneBtn = findViewById(R.id.btn_fresco_2_fit_none);

    mHierarchyBuilder = new GenericDraweeHierarchyBuilder(getResources());

    handleCenter();
    handleCenterCrop();
    handleFocusCrop();
    handleCenterInside();
    handleFitCenter();
    handleFitStart();
    handleFitEnd();
    handleFitXY();
    handleNone();
  }

  /**
   * 居中，无缩放
   */
  private void handleCenter() {
    mCenterBtn.setOnClickListener(v -> {
      GenericDraweeHierarchy hierarchy = mHierarchyBuilder.setActualImageScaleType(ScalingUtils.ScaleType.CENTER).build();
      displayImage(hierarchy);
      mDescTv.setText("居中，无缩放");
    });
  }

  /**
   * 保持宽高比 "缩小"或"放大"，使得两边都大于或等于显示边界，居中显示
   */
  private void handleCenterCrop() {
    mCenterCropBtn.setOnClickListener(v -> {
      GenericDraweeHierarchy hierarchy = mHierarchyBuilder
          .setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP)
          .build();
      displayImage(hierarchy);
      mDescTv.setText("保持宽高比 \"缩小\"或\"放大\"，使得两边都大于或等于显示边界，居中显示");
    });
  }

  /**
   * 同 centerCrop，但居中点不是中点，而是指定的某个点，这里我设置为图片的左上角那个点
   */
  @SuppressLint("SetTextI18n")
  private void handleFocusCrop() {
    mFocusCropBtn.setOnClickListener(v -> {
      PointF point = new PointF(0, 0);
      GenericDraweeHierarchy hierarchy = mHierarchyBuilder
          .setActualImageScaleType(ScalingUtils.ScaleType.FOCUS_CROP)
          .setActualImageFocusPoint(point)
          .build();
      displayImage(hierarchy);
      mDescTv.setText("同 centerCrop，但居中点不是中点，而是指定的某个点，这里我设置为图片的左上角那个点");
    });
  }

  /**
   * 使两边都在显示边界内，居中显示。如果图尺寸大于显示边界，则保持长宽比缩小图片
   */
  private void handleCenterInside() {
    mCenterInsideBtn.setOnClickListener(v -> {
      GenericDraweeHierarchy hierarchy = mHierarchyBuilder
          .setActualImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE)
          .build();
      displayImage(hierarchy);
      mDescTv.setText("使两边都在显示边界内，居中显示。如果图尺寸大于显示边界，则保持长宽比缩小图片");
    });
  }

  /**
   * 保持宽高比，缩小或者放大，使得图片完全显示在边界内，居中显示
   */
  private void handleFitCenter() {
    mFitCenterBtn.setOnClickListener(v -> {
      GenericDraweeHierarchy hierarchy = mHierarchyBuilder
          .setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER)
          .build();
      displayImage(hierarchy);
      mDescTv.setText("保持宽高比，缩小或者放大，使得图片完全显示在边界内，居中显示");
    });
  }

  /**
   * 保持宽高比，缩小或放大，使得图片完全显示在边界内，不居中，和显示边界左上对齐
   */
  private void handleFitStart() {
    mFitStartBtn.setOnClickListener(v -> {
      GenericDraweeHierarchy hierarchy = mHierarchyBuilder
          .setActualImageScaleType(ScalingUtils.ScaleType.FIT_START)
          .build();
      displayImage(hierarchy);
      mDescTv.setText("保持宽高比，缩小或放大，使得图片完全显示在边界内，不居中，和显示边界左上对齐");
    });
  }

  /**
   * 保持宽高比，缩小或放大，使得图片完全显示在边界内，不居中，和显示边界右下对齐
   */
  private void handleFitEnd() {
    mFitEndBtn.setOnClickListener(v -> {
      GenericDraweeHierarchy hierarchy = mHierarchyBuilder
          .setActualImageScaleType(ScalingUtils.ScaleType.FIT_END)
          .build();
      displayImage(hierarchy);
      mDescTv.setText("保持宽高比，缩小或放大，使得图片完全显示在边界内，不居中，和显示边界右下对齐");
    });
  }

  /**
   * 不保持宽高比，填充满，显示边界
   */
  private void handleFitXY() {
    mFitXYBtn.setOnClickListener(v -> {
      GenericDraweeHierarchy hierarchy = mHierarchyBuilder
          .setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY)
          .build();
      displayImage(hierarchy);
      mDescTv.setText("不保持宽高比，填充满，显示边界");
    });
  }

  /**
   * 如果使用 title mode 显示，需要设置为 none
   */
  @SuppressLint("SetTextI18n")
  private void handleNone() {
    mNoneBtn.setOnClickListener(v -> {
      GenericDraweeHierarchy hierarchy = mHierarchyBuilder
          .setActualImageScaleType(null)
          .build();
      displayImage(hierarchy);
      mDescTv.setText("如果使用 title mode 显示，需要设置为 none");
    });
  }


  private void displayImage(GenericDraweeHierarchy hierarchy) {
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