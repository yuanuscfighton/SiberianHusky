package com.laioffer.source;

import android.content.Context;
import android.util.Log;

import com.laioffer.source.binding.inter.Lifecycle;
import com.laioffer.source.binding.inter.LifecycleListener;
import com.laioffer.source.util.LOG;

/**
 * 类描述:
 * <p>
 * created by 西伯利亚哈士奇 on 2024/6/10 17:09
 */
public class RequestManager implements LifecycleListener {

  private Lifecycle mLifecycle;

  public RequestManager(Glide glide, Lifecycle lifecycle, Context applicationContext) {
      mLifecycle = lifecycle;
      mLifecycle.addListener(this); // RequestManager 在构造函数里已经给自己注册了
  }

  // Activity / Fragment 可见时恢复请求
  @Override
  public void onStart() {
    Log.i(LOG.TAG, "开始执行生命周期业务 onStart: 运行队列，全部执行，等待队列，全部清空...");
  }

  @Override
  public void onStop() {
    Log.i(LOG.TAG, "开始执行生命周期业务 onStop: 运行队列，全部停止，把任务都加入到等待队列...");
  }

  @Override
  public void onDestroy() {
    Log.i(LOG.TAG, "开始执行生命周期业务 onDestroy: 自己负责移除自己绑定的生命周期监听，释放操作...");
    mLifecycle.removeListener(this);
  }
}
