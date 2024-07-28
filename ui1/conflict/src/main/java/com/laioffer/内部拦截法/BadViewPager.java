package com.laioffer.内部拦截法;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * 类的描述: 冲突解决方法: 内部拦截法，即在子view中进行拦截
 * Created by 春夏秋冬在中南 on 2023/5/12 08:13
 */
public class BadViewPager extends ViewPager {

  public BadViewPager(@NonNull Context context) {
    super(context);
  }

  public BadViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent event) {
    // 内部拦截法
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      super.onInterceptTouchEvent(event);
      return false;
    }

    return true;
  }


}
