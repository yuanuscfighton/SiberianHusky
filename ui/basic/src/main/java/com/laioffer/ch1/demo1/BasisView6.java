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
 * 类描述: 矩形
 * created by 春夏秋冬在中南 on 2023/5/3 10:39
 */
public class BasisView6 extends View {

  public BasisView6(Context context) {
    super(context);
  }

  public BasisView6(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public BasisView6(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
    // 画矩形
    // 方式1:直接构造
    canvas.drawRect(10, 10, 100, 100, paint);
    // 方式2:使用RectF构造
    paint.setStyle(Paint.Style.FILL);
    RectF rectF = new RectF(210f, 10f, 300f, 100f);
    canvas.drawRect(rectF, paint);

  }
}
