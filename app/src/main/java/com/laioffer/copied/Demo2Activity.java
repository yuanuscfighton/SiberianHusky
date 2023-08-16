package com.laioffer.copied;

import java.util.concurrent.TimeUnit;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.laioffer.app.R;
import com.laioffer.tools.AnimUtils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;

public class Demo2Activity extends AppCompatActivity {

  private static final int ANIM_DURATION_150_MS = 150;
  private static final int ANIM_DURATION_250_MS = 250;
  // 轮播5秒
  private static final int ANIM_REPEAT_COUNT = 12;
  // 动画每间隔10秒播放1次
  private static final int ANIM_DISPLAY_INTERVAL_MS = 10_000;
  // 动画最多播放30分钟
  private static final int ANIM_DISPLAY_MAX_DURATION_MS = 30 * 60 * 1000;

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

  @SuppressLint("CheckResult")
  @SuppressWarnings("ResultOfMethodCallIgnored")
  private void startFlipperAnim() {
    startFlipperAnimOnce();
    Observable.interval(ANIM_DISPLAY_INTERVAL_MS, TimeUnit.MILLISECONDS)
        .timeout(ANIM_DISPLAY_MAX_DURATION_MS, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Long>() {
          @Override
          public void accept(Long ignore) {
            Log.e("测试", "startFlipperAnimOnce()");
            startFlipperAnimOnce();
          }
        });
  }

  private void startFlipperAnimOnce() {
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
        Log.e("测试", "剩余次数:" + mAnimRepeatCount);
        if (mAnimRepeatCount < ANIM_REPEAT_COUNT) {
          mAnimRepeatCount++;
          mFlipperReverseAnimatorSet.start();
        } else {
          mIcon1.setScaleX(1f);
          mIcon1.setAlpha(1f);
          mIcon2.setScaleX(0f);
          mIcon2.setAlpha(0f);
        }
      }
    });
    mFlipperAnimatorSet.start();

    mFlipperReverseAnimatorSet.addListener(new AnimatorListenerAdapter() {
      @Override
      public void onAnimationEnd(Animator animation) {
        mFlipperAnimatorSet.start();
      }
    });
  }
}