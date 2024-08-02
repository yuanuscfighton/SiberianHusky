package com.laioffer.l4_标准的观察者模式;

import static com.laioffer.tools.Constants.TAG4;

import android.util.Log;

/**
 * 观察者的具体实现
 */
public class User implements Observer {

  private final String mUserName;
  private String mMessage;

  public User(String userName) {
    mUserName = userName;
  }

  @Override
  public void update(String s) {
    mMessage = s;
    readMessage();
  }

  private void readMessage() {
    Log.d(TAG4, mUserName + " 阅读了消息: " + mMessage);
  }
}
