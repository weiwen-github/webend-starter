package com.ww.business.test.factorypattern.factory;

import com.ww.business.test.factorypattern.fconst.FruitConst;
import com.ww.business.test.factorypattern.entity.Apple;
import com.ww.business.test.factorypattern.entity.AbstractFruit;
import com.ww.business.test.factorypattern.entity.Grape;
import com.ww.business.test.factorypattern.entity.Strawberry;

/**
 *
 * @author ww
 * @date 2020/11/18
 */
public class FruitFactory {

  /**
   * 将创建实例的工作与使用实例的工作分开，使用者不必关心类对象如何创建，明确了职责
   *
   * @param type
   * @return com.ww.business.test.factorypattern.service.Fruit
   */
  public AbstractFruit getFactory(String type) {

    if (FruitConst.APPLE.equalsIgnoreCase(type)) {
      return new Apple();
    } else if (FruitConst.GRAPE.equalsIgnoreCase(type)) {
      return new Grape();
    } else if (FruitConst.STRAWBERRY.equalsIgnoreCase(type)) {
      return new Strawberry();
    } else {
      return null;
    }
  }
}
