package com.laioffer.ch1.demo2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 类的描述: 弧线路径
 * Created by 春夏秋冬在中南 on 2023/5/18 23:26
 */
public class PathView1 extends View {

  Paint mPaint;
  Path mPath;
  RectF mRectF;

  public PathView1(Context context) {
    this(context, null);
  }

  public PathView1(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public PathView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    mPaint = new Paint();
    mPath = new Path();
    mRectF = new RectF(100, 10, 200, 100);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    mPath.moveTo(10, 10);
    // arcTo(Rect oval, float startAngle, float sweepAngle, boolean forceMoveTo)
    // 是一个画弧的方法，弧线是从椭圆上截取的一部分
    // oval: 生成椭圆的矩形
    // startAngle: 弧开始的角度，以X轴正方向为0°
    // sweepAngle: 弧持续的角度
    // forceMoveTo: 是否强制将弧的起始点作为绘制的起始位置
    mPath.arcTo(mRectF, 0, 90);
    canvas.drawPath(mPath, mPaint);
    // 默认情况下路径都是连贯的
  }
}
