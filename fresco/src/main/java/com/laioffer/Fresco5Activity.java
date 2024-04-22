package com.laioffer;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.laioffer.app.R;

/**
 * Gif 动画图片
 */
public class Fresco5Activity extends AppCompatActivity {

  private final static String ICON_URL =
      "https://th.bing.com/th/id/R.6b5df1bfe0e4778a44dba0753cd169c8?rik=QRQIMqvjWRCO5Q&riu=http%3a%2f%2fpic39.nipic.com%2f20140321%2f8857347_232251363165_2" +
          ".jpg&ehk=7oAaMo6LCHJc%2bqpQ0IPvcH7v69jGRQhb2vDz%2fOd5720%3d&risl=&pid=ImgRaw&r=0";

  private SimpleDraweeView mIconIv;
  private Button mRequestImage;
  private Button mStartGif;
  private Button mStopGif;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fresco_5);
    mIconIv = findViewById(R.id.iv_fresco_5_icon);
    mRequestImage = findViewById(R.id.btn_fresco_5_request);
    mStartGif = findViewById(R.id.btn_fresco_5_start_gif);
    mStopGif = findViewById(R.id.btn_fresco_5_stop_gif);

    requestImage();
    startGif();
    stopGif();
  }

  private void requestImage() {
    mRequestImage.setOnClickListener(v -> {
      Uri uri = Uri.parse(ICON_URL);
      DraweeController controller = Fresco.newDraweeControllerBuilder()
          .setUri(uri)
          .setAutoPlayAnimations(true)
          .setOldController(mIconIv.getController())
          .build();
      mIconIv.setController(controller);
    });
  }

  private void startGif() {
    mStartGif.setOnClickListener(v -> {
      DraweeController controller = mIconIv.getController();
      if (controller != null) {
        Animatable animatable = controller.getAnimatable();
        if (animatable != null && !animatable.isRunning()) {
          animatable.start();
        }
      }
    });
  }

  private void stopGif() {
    mStopGif.setOnClickListener(v -> {
      DraweeController controller = mIconIv.getController();
      if (controller != null) {
        Animatable animatable = controller.getAnimatable();
        if (animatable != null && animatable.isRunning()) {
          animatable.stop();
        }
      }
    });
  }

}
