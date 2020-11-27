package com.ww.business.test.strategypattern.service.impl;

import com.ww.business.test.strategypattern.service.ISeniorMemberStrategyService;
import com.ww.business.test.strategypattern.strateenum.MemberEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 具体策略类 : 高级会员折扣类
 *
 * @author weiwen
 * @date 2020/11/17
 */
@Service
public class SeniorMemberStrategyServiceImpl implements ISeniorMemberStrategyService {

  protected final Logger logger = LoggerFactory.getLogger(this.getClass());
  @Override
  public void advancedMemberSay() {
    System.out.println("我是高级会员");
  }

  @Override
  public double countPrice(double booksPrice) {
    logger.info("高级会员折扣20%");
    return booksPrice * 0.8;
  }
}
