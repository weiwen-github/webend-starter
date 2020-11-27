package com.ww.business.test.factorypattern.entity;

import lombok.Data;

/**
 * @author weiwen
 * @date 2020/11/17
 */
@Data
public class Grape extends AbstractFruit {
  private int seedLest;

  @Override
  public void grow() {
    System.out.println("葡萄生长");
  }

  @Override
  public void harvest() {
    System.out.println("葡萄收获");
  }

  @Override
  public void plant() {
    System.out.println("葡萄种植");
  }
}
