package com.laioffer.ch1.demo1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

/**
 * 类描述: 画两段直线
 * created by 春夏秋冬在中南 on 2023/5/3 09:05
 */
public class BasisView3 extends View {

  public BasisView3(Context context) {
    super(context);
  }

  public BasisView3(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public BasisView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    Paint paint = new Paint();

    // 设置笔画颜色
    paint.setColor(0xFFFF0000);
    // 设置填充样式
    paint.setStyle(Paint.Style.FILL_AND_STROKE);
    // 设置笔画宽度
    paint.setStrokeWidth(5); // 注:当style起作用的时候，setStrokeWidth用于设置描边的宽度; style不起作用的时候，setStrokeWidth用于设置画笔宽度
    // 画直线
    float[] pts = {20, 20, 100, 100, 200, 200, 400, 400};
    canvas.drawLines(pts, paint);
  }
}
