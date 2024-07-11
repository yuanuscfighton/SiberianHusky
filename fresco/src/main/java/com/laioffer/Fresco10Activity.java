package com.laioffer;

import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * 动态添加，并展示图片
 */
public class Fresco10Activity extends AppCompatActivity {

  private final static String ICON_URL =
      "https://th.bing.com/th/id/R.6b5df1bfe0e4778a44dba0753cd169c8?rik=QRQIMqvjWRCO5Q&riu=http%3a%2f%2fpic39.nipic.com%2f20140321%2f8857347_232251363165_2" +
          ".jpg&ehk=7oAaMo6LCHJc%2bqpQ0IPvcH7v69jGRQhb2vDz%2fOd5720%3d&risl=&pid=ImgRaw&r=0";

  private LinearLayout mContainer;
  private SimpleDraweeView mIconIv;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fresco_10);
    mContainer = findViewById(R.id.ll_fresco_10);
    findViewById(R.id.btn_fresco_10_load_more)
        .setOnClickListener(v -> {
          loadMore();
        });

    mIconIv = new SimpleDraweeView(this);
    // 宽高比: 宽度是高度的3倍
    mIconIv.setAspectRatio(3.0f);
  }

  private void loadMore() {
    Uri uri = Uri.parse(ICON_URL);
    ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
        .build();
    PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
        .setOldController(mIconIv.getController())
        .setImageRequest(request)
        .build();
    mIconIv.setController(controller);
    mContainer.addView(mIconIv);

  }
}
