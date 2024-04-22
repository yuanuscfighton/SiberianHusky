package com.laioffer;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.laioffer.app.R;

/**
 * 设置 圆角 / 圆形 图片
 */
public class Fresco3Activity extends AppCompatActivity {

  private final static String ICON_URL =
      "https://th.bing.com/th/id/R.6b5df1bfe0e4778a44dba0753cd169c8?rik=QRQIMqvjWRCO5Q&riu=http%3a%2f%2fpic39.nipic.com%2f20140321%2f8857347_232251363165_2" +
          ".jpg&ehk=7oAaMo6LCHJc%2bqpQ0IPvcH7v69jGRQhb2vDz%2fOd5720%3d&risl=&pid=ImgRaw&r=0";

  private SimpleDraweeView mIconIv;
  private Button mCircleBtn;
  private Button mRoundCornerBtn;

  private GenericDraweeHierarchyBuilder mHierarchyBuilder;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fresco_3);
    mIconIv = findViewById(R.id.iv_fresco_3_icon);
    mCircleBtn = findViewById(R.id.btn_fresco_3_circle);
    mRoundCornerBtn = findViewById(R.id.btn_fresco_3_round_corner);

    mHierarchyBuilder = new GenericDraweeHierarchyBuilder(getResources());

    setCircleImage();
    setRoundCornerImage();
  }

  private void setCircleImage() {
    mCircleBtn.setOnClickListener(v -> {
      RoundingParams params = RoundingParams.asCircle();
      GenericDraweeHierarchy hierarchy = mHierarchyBuilder
          .setRoundingParams(params)
          .build();
      displayImage(hierarchy);
    });

  }

  private void setRoundCornerImage() {
    mRoundCornerBtn.setOnClickListener(v -> {
      RoundingParams params = RoundingParams.fromCornersRadius(50f);
      // 覆盖层
      params.setOverlayColor(getResources().getColor(android.R.color.holo_red_light));
      // 边框
      params.setBorder(getResources().getColor(android.R.color.holo_blue_bright), 5);
      GenericDraweeHierarchy hierarchy = mHierarchyBuilder
          .setRoundingParams(params)
          .build();
      displayImage(hierarchy);
    });
  }

  private void displayImage(GenericDraweeHierarchy hierarchy) {
    mIconIv.setHierarchy(hierarchy);
    Uri uri = Uri.parse(ICON_URL);
    mIconIv.setImageURI(uri);
  }
}
