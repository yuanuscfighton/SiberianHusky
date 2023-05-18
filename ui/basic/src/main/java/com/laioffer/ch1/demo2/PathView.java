package com.laioffer.ch1.demo2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 类的描述: 直线路径
 * Created by 春夏秋冬在中南 on 2023/5/18 23:15
 */
public class PathView extends View {

  Paint mPaint;
  Path mPath;


  public PathView(Context context) {
    this(context, null);
  }

  public PathView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    mPaint = new Paint();
    mPath = new Path();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    mPaint.setColor(Color.RED);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeWidth(5);

    // 设置起始点
    mPath.moveTo(10, 10);
    // 第1条直线的终点，也是第2条直线的起始点
    mPath.lineTo(10, 100);
    // 画第2条直线
    mPath.lineTo(300, 100);
    // 闭环
    mPath.close();

    canvas.drawPath(mPath, mPaint);
  }
}
