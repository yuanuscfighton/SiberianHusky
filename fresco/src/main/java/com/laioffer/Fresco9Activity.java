package com.laioffer;

import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.laioffer.app.R;

/**
 * 图片缩放
 */
public class Fresco9Activity extends AppCompatActivity {

  private final static String ICON_URL =
      "https://th.bing.com/th/id/R.6b5df1bfe0e4778a44dba0753cd169c8?rik=QRQIMqvjWRCO5Q&riu=http%3a%2f%2fpic39.nipic.com%2f20140321%2f8857347_232251363165_2" +
          ".jpg&ehk=7oAaMo6LCHJc%2bqpQ0IPvcH7v69jGRQhb2vDz%2fOd5720%3d&risl=&pid=ImgRaw&r=0";

  private SimpleDraweeView mIconIv;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fresco_9);
    mIconIv = findViewById(R.id.iv_fresco_9_icon);

    // 按钮: 修改内存中图片大小
    findViewById(R.id.btn_fresco_9_resize)
        .setOnClickListener(v -> resize());

    // 按钮: 旋转图片
    findViewById(R.id.btn_fresco_9_rotate)
        .setOnClickListener(v -> rotate());
  }

  private void resize() {
    Uri uri = Uri.parse(ICON_URL);

    ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
        // 重新设置图片尺寸
        .setResizeOptions(new ResizeOptions(50, 50))
        .build();

    PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
        .setOldController(mIconIv.getController())
        .setImageRequest(request)
        .build();
    mIconIv.setController(controller);
  }

  private void rotate() {
    Uri uri = Uri.parse(ICON_URL);
    ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
        .setRotationOptions(RotationOptions.autoRotate())
        .build();
    DraweeController controller = Fresco.newDraweeControllerBuilder()
        .setOldController(mIconIv.getController())
        .setImageRequest(request)
        .build();
    mIconIv.setController(controller);
  }
}
