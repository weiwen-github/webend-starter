package com.ww.business.test.observerpattern;

/**
 * 被观察者
 *
 * @author ww
 * @date 2020/11/17
 */
public interface Observerable {
  void registerObserver(Observer o);

  void removeObserver(Observer o);

  void notifyObserver();
}
