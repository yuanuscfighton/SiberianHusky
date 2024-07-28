package com.laioffer.badviewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class BadViewPager extends ViewPager {

  public BadViewPager(@NonNull Context context) {
    super(context);
  }

  public BadViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent event) {
    /**
     * 1.可以上下滑动，不能左右滑动
     */
    // return false;

    /**
     * 2.不能上下滑动，只能左右滑动
     */
    // return true;

    /**
     * 3.既可以上下滑动，也可以左右滑动
     */
    return super.onInterceptTouchEvent(event);

    /**
     * 滑动冲突解决思路:想要把事件分发给谁就分发给谁
     */
  }


}
