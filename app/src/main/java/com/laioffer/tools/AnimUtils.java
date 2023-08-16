package com.laioffer.tools;

import android.animation.Animator;

import io.reactivex.rxjava3.annotations.Nullable;

public class AnimUtils {

  public static void stopAnimatorSafely(@Nullable Animator animator) {
    if (animator != null && animator.isRunning()) {
      animator.removeAllListeners();
      animator.end();
    }
  }
}
