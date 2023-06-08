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
 * 类描述: 椭圆
 * created by 春夏秋冬在中南 on 2023/5/3 10:55
 */
public class BasisView8 extends View {

  public BasisView8(Context context) {
    super(context);
  }

  public BasisView8(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public BasisView8(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
    // 画椭圆
    RectF rectF = new RectF(100, 50, 300, 150);
    canvas.drawRect(rectF, paint);

    paint.setColor(Color.GREEN);
    // 根据同一个矩形画椭圆
    canvas.drawOval(rectF, paint);
  }
}
