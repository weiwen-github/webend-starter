package com.ww.business.test.observerpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者
 *
 * @author ww
 * @date 2020/11/17
 */
public class ObserverableService implements Observerable {

  // 注意到这个List集合的泛型参数为Observer接口，设计原则：面向接口编程而不是面向实现编程

  private final List<Observer> list;
  private String message;

  public ObserverableService() {
    list = new ArrayList<>();
  }

  @Override
  public void registerObserver(Observer o) {
    list.add(o);
  }

  @Override
  public void removeObserver(Observer o) {
    if (!list.isEmpty()) {
      list.remove(o);
    }
  }

  @Override
  public void notifyObserver() {
    for (int i = 0; i < list.size(); i++) {
      Observer o = list.get(i);
      o.update(message);
    }
  }

  public void setInfomation(String s) {
    this.message = s;
    System.out.println("更新消息： " + s);
    // 消息更新，通知所有观察者
    notifyObserver();
  }
}
