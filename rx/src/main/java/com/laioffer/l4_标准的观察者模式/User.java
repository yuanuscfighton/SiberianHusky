package com.laioffer.l4_标准的观察者模式;

import static com.laioffer.tools.Constants.TAG4;

import android.util.Log;

/**
 * 类的描述: 观察者的具体实现
 * Created by 春夏秋冬在中南 on 2023/7/30 18:42
 */
public class User implements Observer {

  private String mUserName;
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
    Log.d(TAG4, "用户阅读了消息，" + mMessage);
  }
}
