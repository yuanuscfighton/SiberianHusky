package com.laioffer.l4_标准的观察者模式;

/**
 * 抽象层 被观察者
 */
public interface Observable {

  void addObserver(Observer o);

  void removeObserver(Observer o);

  /**
   * 被观察者发生了改变，通知观察者
   */
  void notifyObservers();

  /**
   * 微信公众号的服务 发布了一条消息
   */
  void pushMessage(String message);

}
