package com.laioffer.lesson4_标准的观察者模式;

/**
 * 抽象层，观察者
 */
public interface Observer {

  /**
   * 被观察者改变了，观察者可以感知到
   */
  void update(String s);
}
