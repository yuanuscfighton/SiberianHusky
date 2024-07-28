package com.laioffer.ch1.demo1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;

/**
 * 类描述: 判断是否包含某个点
 * created by 春夏秋冬在中南 on 2023/5/3 11:17
 */
public class BasisView10 extends View {

  private int mX, mY;
  private Paint mPaint;
  private Rect mRect;

  public BasisView10(Context context) {
    this(context, null);
  }

  public BasisView10(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public BasisView10(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    // 应用: 当手指在矩形区域内点击的时候，矩形边框是红色的; 当手指在矩形区域外点击的时候，矩形边框是绿色的
    if (mRect.contains(mX, mY)) {
      mPaint.setColor(Color.RED);
    } else {
      mPaint.setColor(Color.CYAN);
    }
    canvas.drawRect(mRect, mPaint);
  }

  @SuppressLint("ClickableViewAccessibility")
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    // 获取 当前手指在控件中的坐标
    mX = (int) event.getX();
    mY = (int) event.getY();
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      invalidate();
      // 返回true，表示当前控件已经在拦截(消费)了这个消息
      return true;
    } else if (event.getAction() == MotionEvent.ACTION_UP) {
      mX = -1;
      mY = -1;
    }
    postInvalidate();

    // 后续的ACTION_MOVE、ACTION_UP消息仍然继续传过来，如果返回false(系统默认返回false)，就表示当前控件不需要这个消息，
    // 那么后续的ACTION_MOVE、ACTION_UP消息就不会再传到这个控件.
    return super.onTouchEvent(event);
  }

  private void init() {
    mPaint = new Paint();
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeWidth(20);
    mRect = new Rect(100, 50, 500, 450);
  }
}
