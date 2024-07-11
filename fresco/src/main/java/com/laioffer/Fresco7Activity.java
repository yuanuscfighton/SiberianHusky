package com.laioffer;

import android.annotation.SuppressLint;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * 图片加载的监听
 */
public class Fresco7Activity extends AppCompatActivity {

  private final static String ICON_URL =
      "https://th.bing.com/th/id/R.6b5df1bfe0e4778a44dba0753cd169c8?rik=QRQIMqvjWRCO5Q&riu=http%3a%2f%2fpic39.nipic.com%2f20140321%2f8857347_232251363165_2" +
          ".jpg&ehk=7oAaMo6LCHJc%2bqpQ0IPvcH7v69jGRQhb2vDz%2fOd5720%3d&risl=&pid=ImgRaw&r=0";

  private SimpleDraweeView mIconIv;
  private Button mLoadBtn;
  private TextView mTv1;
  private TextView mTv2;

  private ControllerListener<ImageInfo> mControllerListener = new BaseControllerListener<ImageInfo>() {
    // 加载图片完成
    @SuppressLint("SetTextI18n")
    @Override
    public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
      super.onFinalImageSet(id, imageInfo, animatable);
      if (imageInfo == null) {
        return;
      }
      // 获取图片的质量
      QualityInfo qualityInfo = imageInfo.getQualityInfo();
      mTv1.setText("Final image received! "
          + "\n size: " + imageInfo.getWidth()
          + "x" + imageInfo.getHeight()
          + "\n Quality level: " + qualityInfo.getQuality()
          + "\n good enough: "
          + qualityInfo.isOfGoodEnoughQuality()
          + "\n full quality: "
          + qualityInfo.isOfFullQuality());
    }

    // 渐进式加载图片
    @SuppressLint("SetTextI18n")
    @Override
    public void onIntermediateImageSet(String id, ImageInfo imageInfo) {
      super.onIntermediateImageSet(id, imageInfo);
      mTv2.setText("IntermediateImageSet image received");
    }

    // 加载图片失败
    @SuppressLint("SetTextI18n")
    @Override
    public void onFailure(String id, Throwable throwable) {
      super.onFailure(id, throwable);
      mTv1.setText("Error loading " + id);
    }
  };

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fresco_7);
    mIconIv = findViewById(R.id.iv_fresco_7_icon);
    mLoadBtn = findViewById(R.id.btn_fresco_7_load);
    mTv1 = findViewById(R.id.tv_fresco_7_listener1);
    mTv2 = findViewById(R.id.tv_fresco_7_listener2);

    load();
  }

  private void load() {
    mLoadBtn.setOnClickListener(v -> {
      // 图片质量配置
      ProgressiveJpegConfig jpegConfig = new ProgressiveJpegConfig() {
        @Override
        public int getNextScanNumberToDecode(int scanNumber) {
          return scanNumber + 2;
        }

        @Override
        public QualityInfo getQualityInfo(int scanNumber) {
          boolean isGoodEnough = (scanNumber >= 5);
          return ImmutableQualityInfo.of(scanNumber, isGoodEnough, false);
        }

        @Override
        public boolean decodeProgressively() {
          return false;
        }
      };

      ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig
          .newBuilder(this)
          .setProgressiveJpegConfig(jpegConfig)
          .build();

      Uri uri = Uri.parse(ICON_URL);

      ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(uri)
          .setProgressiveRenderingEnabled(true)
          .build();

      DraweeController controller = Fresco.newDraweeControllerBuilder()
          .setOldController(mIconIv.getController())
          .setImageRequest(imageRequest)
          .build();
      mIconIv.setController(controller);
    });
  }
}
