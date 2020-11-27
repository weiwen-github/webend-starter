package com.ww.business.test.factorypattern.entity;

import lombok.Data;

/**
 * @author weiwen
 * @date 2020/11/17
 */
@Data
public class Apple extends AbstractFruit {
  private int treeAge;

  @Override
  public void grow() {
    System.out.println("苹果生长");
  }

  @Override
  public void harvest() {
    System.out.println("苹果收获");
  }

  @Override
  public void plant() {
    System.out.println("苹果种植");
  }
}
