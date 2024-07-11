package com.laioffer;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;

/**
 * 修改图片
 */
public class Fresco8Activity extends AppCompatActivity {

  private final static String ICON_URL =
      "https://th.bing.com/th/id/R.6b5df1bfe0e4778a44dba0753cd169c8?rik=QRQIMqvjWRCO5Q&riu=http%3a%2f%2fpic39.nipic.com%2f20140321%2f8857347_232251363165_2" +
          ".jpg&ehk=7oAaMo6LCHJc%2bqpQ0IPvcH7v69jGRQhb2vDz%2fOd5720%3d&risl=&pid=ImgRaw&r=0";


  private SimpleDraweeView mIconIv;
  private Button mModifyBtn;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fresco_8);
    mIconIv = findViewById(R.id.iv_fresco_8_icon);
    mModifyBtn = findViewById(R.id.btn_fresco_8_modify);

    modify();
  }

  private void modify() {
    mModifyBtn.setOnClickListener(v -> {
      Uri uri = Uri.parse(ICON_URL);

      Postprocessor postprocessor = new BasePostprocessor() {
        @Override
        public String getName() {
          return "postprocessor";
        }

        @Override
        public void process(Bitmap bitmap) {
          for (int x = 0; x < bitmap.getWidth(); x++) {
            for (int y = 0; y < bitmap.getHeight(); y++) {
              bitmap.setPixel(x, y, Color.RED);
            }
          }
        }
      };

      ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
          .setPostprocessor(postprocessor)
          .build();
      PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
          .setOldController(mIconIv.getController())
          .setImageRequest(request)
          .build();
      mIconIv.setController(controller);
    });
  }
}
