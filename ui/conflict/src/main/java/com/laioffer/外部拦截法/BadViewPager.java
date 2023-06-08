package com.laioffer.外部拦截法;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class BadViewPager extends ViewPager {

  private int mLastX, mLastY;

  public BadViewPager(@NonNull Context context) {
    super(context);
  }

  public BadViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent event) {

    // 外部拦截法
    int x = (int)event.getX();
    int y = (int)event.getY();

    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN: {
        mLastX = (int)event.getX();
        mLastY = (int)event.getY();
        break;
      }
      case MotionEvent.ACTION_MOVE: {
        int deltaX = x - mLastX;
        int deltaY = y - mLastY;
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
          return true;
        }
        break;
      }
      case MotionEvent.ACTION_UP: {
        break;
      }
      default:
        break;
    }

    return super.onInterceptTouchEvent(event);
  }


}
