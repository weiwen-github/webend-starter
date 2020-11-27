package com.ww.business.test.strategypattern.service;

import com.ww.business.test.strategypattern.strateenum.MemberEnum;

/**
 * 抽象策略类
 *
 * @author weiwen
 * @date 2020/11/17
 */
public interface MemberStrategy {

  /**
   * 策略方法 ：计算图书的价格
   *
   * @param booksPrice
   * @return double
   */
  double countPrice(double booksPrice);
}
