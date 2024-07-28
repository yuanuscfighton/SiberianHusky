package com.laioffer.ch1.demo1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

/**
 * 类描述: 画点，offset
 * created by 春夏秋冬在中南 on 2023/5/3 09:05
 */
public class BasisView5 extends View {

  public BasisView5(Context context) {
    super(context);
  }

  public BasisView5(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public BasisView5(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
    paint.setStrokeWidth(15);
    // 画点
    float[] pts = {10, 10, 100, 100, 200, 200, 400, 400};
    canvas.drawPoints(pts, 2, 4, paint);
  }
}
