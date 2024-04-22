package com.laioffer;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.laioffer.app.R;

/**
 * 渐进式加载图片
 */
public class Fresco4Activity extends AppCompatActivity {

  private final static String ICON_URL =
      "https://th.bing.com/th/id/R.6b5df1bfe0e4778a44dba0753cd169c8?rik=QRQIMqvjWRCO5Q&riu=http%3a%2f%2fpic39.nipic.com%2f20140321%2f8857347_232251363165_2" +
          ".jpg&ehk=7oAaMo6LCHJc%2bqpQ0IPvcH7v69jGRQhb2vDz%2fOd5720%3d&risl=&pid=ImgRaw&r=0";

  private SimpleDraweeView mIconIv;
  private Button mRequestImage;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fresco_4);
    mIconIv = findViewById(R.id.iv_fresco_4_icon);
    mRequestImage = findViewById(R.id.btn_fresco_4_request);

    loadImage();
  }

  private void loadImage() {
    mRequestImage.setOnClickListener(v -> {
      // 加载质量分配
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
          return true;
        }
      };

      ImagePipelineConfig.newBuilder(this).setProgressiveJpegConfig(jpegConfig).build();
      Uri uri = Uri.parse(ICON_URL);

      // 获取图片请求
      ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
          .setProgressiveRenderingEnabled(true)
          .build();

      // 1.设置加载的控制
      DraweeController controller = Fresco.newDraweeControllerBuilder()
          .setImageRequest(request)
          .setTapToRetryEnabled(true)
          .setOldController(mIconIv.getController()) // 使用 oldController 可以节省不要的内存分配
          .build();
      mIconIv.setController(controller);
    });
  }
}
