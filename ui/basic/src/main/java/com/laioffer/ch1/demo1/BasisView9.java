package com.laioffer.ch1.demo1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

/**
 * 类描述: 弧
 * created by 春夏秋冬在中南 on 2023/5/3 11:11
 */
public class BasisView9 extends View {

  public BasisView9(Context context) {
    super(context);
  }

  public BasisView9(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public BasisView9(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
    paint.setStrokeWidth(5);
    // 画弧
    // 1.带两边
    RectF rectF1 = new RectF(50, 50, 150, 150);
    canvas.drawArc(rectF1, 0, 90, true, paint);

    // 2.不带两边
    RectF rectF2 = new RectF(150, 50, 250, 150);
    canvas.drawArc(rectF2, 0, 90, false, paint);
  }
}
