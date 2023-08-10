package com.laioffer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.laioffer.app.R;
import com.laioffer.tools.AnimUtils;

public class Demo1Activity extends AppCompatActivity {

  private static final int ANIM_DURATION_150_MS = 1500;
  private static final int ANIM_DURATION_250_MS = 2500;
  private static final int ANIM_REPEAT_COUNT = 5;

  private ImageView mIcon1;
  private ImageView mIcon2;

  private int mAnimRepeatCount = 0;
  private AnimatorSet mFlipperAnimatorSet;
  private AnimatorSet mFlipperReverseAnimatorSet;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_demo1);
    mIcon1 = findViewById(R.id.iv_icon_1);
    mIcon2 = findViewById(R.id.iv_icon_2);

    findViewById(R.id.start_anim).setOnClickListener(v -> startFlipperAnim());

    mIcon1.setScaleX(1f);
    mIcon1.setAlpha(1f);
    mIcon2.setScaleX(0f);
    mIcon2.setAlpha(0f);

  }

  private void startFlipperAnim() {
    mAnimRepeatCount = 0;
    AnimUtils.stopAnimatorSafely(mFlipperAnimatorSet);
    AnimUtils.stopAnimatorSafely(mFlipperReverseAnimatorSet);

    ////////// 正向播放 //////////
    // icon1的缩小动画
    ObjectAnimator smallerScaleXAnimator = ObjectAnimator.ofFloat(mIcon1, View.SCALE_X, 1f, 0f);
    smallerScaleXAnimator.setInterpolator(new LinearInterpolator());
    smallerScaleXAnimator.setDuration(ANIM_DURATION_150_MS);

    // icon1的渐隐动画
    ObjectAnimator alphaOutAnimator = ObjectAnimator.ofFloat(mIcon1, View.ALPHA, 1f, 0f);
    alphaOutAnimator.setInterpolator(new LinearInterpolator());
    alphaOutAnimator.setDuration(ANIM_DURATION_150_MS);

    // icon2的放大动画
    ObjectAnimator biggerScaleXAnimator = ObjectAnimator.ofFloat(mIcon2, View.SCALE_X, 0f, 1);
    biggerScaleXAnimator.setInterpolator(new LinearInterpolator());
    biggerScaleXAnimator.setDuration(ANIM_DURATION_250_MS);

    // icon2的渐显动画
    ObjectAnimator alphaInAnimator = ObjectAnimator.ofFloat(mIcon2, View.ALPHA, 0f, 1f);
    alphaInAnimator.setInterpolator(new LinearInterpolator());
    alphaInAnimator.setDuration(ANIM_DURATION_150_MS);

    ////////// 反向播放 //////////
    // icon2的缩小动画
    ObjectAnimator smallerScaleXReverseAnimator = ObjectAnimator.ofFloat(mIcon2, View.SCALE_X, 1f, 0f);
    smallerScaleXReverseAnimator.setInterpolator(new LinearInterpolator());
    smallerScaleXReverseAnimator.setDuration(ANIM_DURATION_150_MS);

    // icon2的渐隐动画
    ObjectAnimator alphaOutReverseAnimator = ObjectAnimator.ofFloat(mIcon2, View.ALPHA, 1f, 0f);
    alphaOutReverseAnimator.setInterpolator(new LinearInterpolator());
    alphaOutReverseAnimator.setDuration(ANIM_DURATION_150_MS);

    // icon1的放大动画
    ObjectAnimator biggerScaleXReverseAnimator = ObjectAnimator.ofFloat(mIcon1, View.SCALE_X, 0f, 1f);
    biggerScaleXReverseAnimator.setInterpolator(new LinearInterpolator());
    biggerScaleXReverseAnimator.setDuration(ANIM_DURATION_250_MS);

    // icon1的渐显动画
    ObjectAnimator alphaInReverseAnimator = ObjectAnimator.ofFloat(mIcon1, View.ALPHA, 0f, 1f);
    alphaInReverseAnimator.setInterpolator(new LinearInterpolator());
    alphaInReverseAnimator.setDuration(ANIM_DURATION_150_MS);

    mFlipperAnimatorSet = new AnimatorSet();
    mFlipperAnimatorSet.play(smallerScaleXAnimator)
        .with(alphaOutAnimator)
        .with(alphaInAnimator)
        .before(biggerScaleXAnimator);

    mFlipperReverseAnimatorSet = new AnimatorSet();
    mFlipperReverseAnimatorSet.play(smallerScaleXReverseAnimator)
        .with(alphaOutReverseAnimator)
        .with(alphaInReverseAnimator)
        .before(biggerScaleXReverseAnimator);
    mFlipperAnimatorSet.addListener(new AnimatorListenerAdapter() {
      @Override
      public void onAnimationEnd(Animator animation) {
        if (mAnimRepeatCount < ANIM_REPEAT_COUNT) {
          mAnimRepeatCount++;
          mFlipperReverseAnimatorSet.start();
        }
      }
    });
    mFlipperAnimatorSet.start();

    mFlipperReverseAnimatorSet.addListener(new AnimatorListenerAdapter() {
      @Override
      public void onAnimationEnd(Animator animation) {
        if (mAnimRepeatCount < ANIM_REPEAT_COUNT) {
          mAnimRepeatCount++;
          mFlipperAnimatorSet.start();
        }
      }
    });
  }
}