package com.laioffer.ch1.demo1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

/**
 * 类描述: 第1个自定义view
 * created by 春夏秋冬在中南 on 2023/5/2 16:30
 */
public class BasisView extends View {

  public BasisView(Context context) {
    super(context);
  }

  public BasisView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public BasisView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    Paint paint = new Paint();

    // 设置笔画颜色
    paint.setColor(Color.RED);
    // 设置填充样式
    paint.setStyle(Paint.Style.STROKE);
    // 设置笔画宽度
    paint.setStrokeWidth(50);
    // 打开抗锯齿功能
    paint.setAntiAlias(true);
    // 画圆
    canvas.drawCircle(190, 200, 150, paint);
  }
}
