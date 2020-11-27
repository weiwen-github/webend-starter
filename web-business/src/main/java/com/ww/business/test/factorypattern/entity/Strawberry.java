package com.ww.business.test.factorypattern.entity;

import lombok.Data;

/**
 * @author weiwen
 * @date 2020/11/17
 */
@Data
public class Strawberry extends AbstractFruit {
  private int coteLess;

  @Override
  public void grow() {
    System.out.println("草莓生长");
  }

  @Override
  public void harvest() {
    System.out.println("草莓收获");
  }

  @Override
  public void plant() {
    System.out.println("草莓种植");
  }
}
