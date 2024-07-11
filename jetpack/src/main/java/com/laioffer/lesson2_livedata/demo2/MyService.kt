package com.laioffer.lesson2_livedata.demo2

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlin.concurrent.thread

/**
 * 类描述: 模拟微信后台推送消息
 *
 * created by 春夏秋冬在中南 on 2024/5/15 22:58
 */
class MyService : Service() {

  override fun onBind(intent: Intent): IBinder? = null

  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    thread {
      for (x in 1..100000) {
        Log.e("server", "服务器给推你推送消息啦(叮咚声响),消息内容是:${x}")
        MyLiveData.data1.postValue("服务器给推你推送消息啦,消息内容是:${x}")
        Thread.sleep(5000) // 5秒钟推一次
      }
    }
    return super.onStartCommand(intent, flags, startId)
  }
}