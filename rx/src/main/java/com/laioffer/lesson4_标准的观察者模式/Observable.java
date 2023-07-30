package com.laioffer.lesson4_标准的观察者模式;

/**
 * 类的描述: 抽象层 被观察者
 * Created by 春夏秋冬在中南 on 2023/7/30 18:36
 */
public interface Observable {

  void addObserver(Observer o);

  void removeObserver(Observer o);

  /**
   * 被观察者发出了改变
   */
  void notifyObservers();

  /**
   * 微信公众号的服务 发布了一条消息
   */
  void pushMessage(String message);

}
