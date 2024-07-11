package com.laioffer.source.binding;

import androidx.annotation.NonNull;

import com.laioffer.source.binding.inter.Lifecycle;
import com.laioffer.source.binding.inter.LifecycleListener;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * 类描述: 非 Application 作用域（绿色作用域）
 * <p>
 * created by 西伯利亚哈士奇 on 2024/6/10 21:41
 */
public class ActivityFragmentLifecycle implements Lifecycle {

  // 容器
  private final Set<LifecycleListener> lifecycleListeners =
      Collections.newSetFromMap(new WeakHashMap<LifecycleListener, Boolean>());
  private boolean isStarted; // 启动的标记
  private boolean isDestroyed; // 销毁的标记

  @Override
  public void addListener(@NonNull LifecycleListener listener) {
    lifecycleListeners.add(listener);

    if (isDestroyed) {
      listener.onDestroy();
    } else if (isStarted) {
      listener.onStart();
    } else {
      listener.onStop(); // 首次启动：会默认 onStop 先停止，然后再 onStart
    }
  }

  @Override
  public void removeListener(@NonNull LifecycleListener listener) {
    lifecycleListeners.remove(listener);
  }

  void onStart() {
    isStarted = true;
    for (LifecycleListener lifecycleListener : lifecycleListeners) {
      lifecycleListener.onStart();
    }
  }

  void onStop() {
    isStarted = false;
    for (LifecycleListener lifecycleListener : lifecycleListeners) {
      lifecycleListener.onStop();
    }
  }

  void onDestroy() {
    isDestroyed = true;
    for (LifecycleListener lifecycleListener : lifecycleListeners) {
      lifecycleListener.onDestroy();
    }
  }
}
