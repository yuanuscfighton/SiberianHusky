package com.laioffer.source.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Looper;

import androidx.annotation.NonNull;

/**
 * 类描述:
 * <p>
 * created by 西伯利亚哈士奇 on 2024/6/10 17:12
 */
public class Util {

  public static boolean isOnMainThread() {
    return Looper.myLooper() == Looper.getMainLooper();
  }

  public static boolean isOnBackgroundThread() {
    return !isOnMainThread();
  }

  @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
  public static void assertNotDestroyed(@NonNull Activity activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed()) {
      throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
    }
  }
}
