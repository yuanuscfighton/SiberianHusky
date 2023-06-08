package com.laioffer.ch1.demo1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

/**
 * 类描述: 圆角矩形
 * created by 春夏秋冬在中南 on 2023/5/3 10:43
 */
public class BasisView7 extends View {

  public BasisView7(Context context) {
    super(context);
  }

  public BasisView7(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public BasisView7(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @SuppressLint("DrawAllocation")
  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    Paint paint = new Paint();

    // 设置笔画颜色
    paint.setColor(0xFFFF0000);
    // 设置填充样式
    paint.setStyle(Paint.Style.STROKE);
    // 设置笔画宽度
    paint.setStrokeWidth(15);
    // 画圆角矩形
    RectF rectF = new RectF(100, 50, 300, 150);
    canvas.drawRoundRect(rectF, 20, 10, paint);

  }
}
