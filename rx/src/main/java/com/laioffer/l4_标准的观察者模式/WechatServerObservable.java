package com.laioffer.l4_标准的观察者模式;

import static com.laioffer.tools.Constants.TAG4;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 类的描述: 具体的被观察者角色，e.g. 微信服务号，当消息有更新的时候，将消息推送给观察者
 * Created by 春夏秋冬在中南 on 2023/7/30 18:39
 */
public class WechatServerObservable implements Observable {

  private final List<Observer> mObserversList = new ArrayList<>();
  private String mMessage;

  @Override
  public void addObserver(Observer o) {
    mObserversList.add(o);
  }

  @Override
  public void removeObserver(Observer o) {
    mObserversList.remove(o);
  }

  @Override
  public void notifyObservers() {
    for (Observer o : mObserversList) {
      o.update(mMessage);
    }
  }

  public void pushMessage(@NonNull String message) {
    mMessage = message;
    // 通知所有关注了本服务号的小伙伴
    Log.d(TAG4, "微信服务号更新消息了，message=" + message);
    notifyObservers();
  }
}
