package com.ww.business.controller;

import com.ww.business.test.factorypattern.entity.AbstractFruit;
import com.ww.business.test.factorypattern.factory.FruitFactory;
import com.ww.business.test.factorypattern.fconst.FruitConst;
import com.ww.business.test.observerpattern.ObserverableService;
import com.ww.business.test.observerpattern.User;
import org.apache.commons.lang.ArrayUtils;

/**
 * @author ww
 * @date 2020/11/16
 */
public class TestController {

  public static void main(String[] args) {
    int[] c = {1, 2, 3};

    System.out.println(ArrayUtils.toString(c, ","));

    System.out.println("---------工厂模式-------------------");
    FruitFactory factory = new FruitFactory();
    AbstractFruit apple = factory.getFactory(FruitConst.APPLE);
    apple.plant();
    AbstractFruit grade = factory.getFactory(FruitConst.GRAPE);
    grade.harvest();
    AbstractFruit strawberry = factory.getFactory(FruitConst.STRAWBERRY);
    strawberry.grow();

    System.out.println("---------观察者模式-------------------");
    User zs = new User("zs");
    User ls = new User("ls");
    User ww = new User("ww");
    ObserverableService service = new ObserverableService();
    service.registerObserver(zs);
    service.registerObserver(ls);
    service.registerObserver(ww);
    service.setInfomation("嘎嘎嘎嘎公告");

    System.out.println("============================");
    service.removeObserver(ls);
    service.setInfomation("kkkkkkkkkkkkk");

    // System.out.println("--------单例模式--------");
    // for (int i = 0; i < 10; i++) {
    //  new Thread(
    //          new Runnable() {
    //            @Override
    //            public void run() {
    //              MingEmperor.getEmperor().sayCommand("皇帝");
    //            }
    //          })
    //      .start();
    // }
  }
}
