package com.laioffer.source.binding;

import androidx.annotation.NonNull;

import com.laioffer.source.binding.inter.Lifecycle;
import com.laioffer.source.binding.inter.LifecycleListener;

/**
 * 类描述: Application 作用域
 * <p>
 * created by 西伯利亚哈士奇 on 2024/6/10 17:30
 */
public class ApplicationLifecycle implements Lifecycle {

  /**
   * 子线程中，在实例化 RequestManager 的时候，会执行 this.lifecycle.addListener(this);
   * 此时，由于是子线程，无法搞一个空白的 fragment 覆盖山去，也意味着无法监听 Activity / Fragment
   * 所以只能是在 addListener 的时候，手动的 onStart 了
   */
  @Override
  public void addListener(@NonNull LifecycleListener listener) {

  }

  /**
   * 子线程中，由于无法搞一个空白的 fragment 覆盖上去，也意味着无法监听 Activity / Fragment
   * 那么只能是属于全局大范围的跟随 App 进程的灭亡而灭亡了，所以啥事也干不了
   */
  @Override
  public void removeListener(@NonNull LifecycleListener listener) {

  }
}
