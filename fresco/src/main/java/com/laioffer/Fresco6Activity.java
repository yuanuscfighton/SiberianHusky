package com.laioffer;

import java.io.File;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.laioffer.app.R;

/**
 * 多图请求及图片复用
 */
public class Fresco6Activity extends AppCompatActivity {

  private final static String LOW_ICON_URL = "https://img.zcool.cn/community/01e4315542ab990000019ae99f4ef0.jpg@1280w_1l_2o_100sh.jpg";

  private final static String ICON_URL =
      "https://th.bing.com/th/id/R.6b5df1bfe0e4778a44dba0753cd169c8?rik=QRQIMqvjWRCO5Q&riu=http%3a%2f%2fpic39.nipic.com%2f20140321%2f8857347_232251363165_2" +
          ".jpg&ehk=7oAaMo6LCHJc%2bqpQ0IPvcH7v69jGRQhb2vDz%2fOd5720%3d&risl=&pid=ImgRaw&r=0";


  private SimpleDraweeView mIconIv;
  private Button mStepLoadBtn;
  private Button mPreviewBtn;
  private Button mReuseBtn;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fresco_6);
    mIconIv = findViewById(R.id.iv_fresco_6_icon);
    mStepLoadBtn = findViewById(R.id.btn_fresco_6_step_load);
    mPreviewBtn = findViewById(R.id.btn_fresco_6_preview);
    mReuseBtn = findViewById(R.id.btn_fresco_6_reuse);

    stepLoad();
    preview();
    reuse();
  }

  /**
   * 先展示低分辨率的图，然后是高分辨率图
   */
  private void stepLoad() {
    mStepLoadBtn.setOnClickListener(v -> {
      Uri lowUri = Uri.parse(LOW_ICON_URL);
      Uri highUri = Uri.parse(ICON_URL);
      DraweeController controller = Fresco.newDraweeControllerBuilder()
          .setLowResImageRequest(ImageRequest.fromUri(lowUri)) // 低分辨率图片。当网不好的时候，优先展示低分辨率图片
          .setImageRequest(ImageRequest.fromUri(highUri)) // 最终的图片
          .build();
      mIconIv.setController(controller);
    });
  }

  /**
   * 本地缩略图预览
   */
  private void preview() {
    mPreviewBtn.setOnClickListener(v -> {
      Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/icon2.png"));
      ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
          .setLocalThumbnailPreviewsEnabled(true)
          .build();
      DraweeController controller = Fresco.newDraweeControllerBuilder()
          .setImageRequest(request)
          .build();
      mIconIv.setController(controller);
    });
  }

  /**
   * 本地图片复用
   */
  private void reuse() {
    mReuseBtn.setOnClickListener(v -> {
      // 在请求之前，还会去内存中请求一次图片，没有才会先去本地，最后取网络uri
      // 本地准备复用图片的uri，如果本地这个图片不存在，会自动去下载一个uri

      ImageRequest request1 = ImageRequest.fromUri(LOW_ICON_URL);
      ImageRequest request2 = ImageRequest.fromUri(ICON_URL);
      ImageRequest[] requests = {request1, request2};

      DraweeController controller = Fresco.newDraweeControllerBuilder()
          .setFirstAvailableImageRequests(requests)
          .setOldController(mIconIv.getController())
          .build();
      mIconIv.setController(controller);
    });
  }
}

