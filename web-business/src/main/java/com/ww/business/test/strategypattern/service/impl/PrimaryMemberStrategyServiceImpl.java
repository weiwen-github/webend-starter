package com.ww.business.test.strategypattern.service.impl;

import com.ww.business.test.strategypattern.service.IPrimaryMemberStrategyService;
import com.ww.business.test.strategypattern.strateenum.MemberEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 具体策略类 : 初级会员折扣类
 *
 * @author ww
 * @date 2020/11/17
 */
@Service
public class PrimaryMemberStrategyServiceImpl implements IPrimaryMemberStrategyService {
  protected final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public double countPrice(double booksPrice) {
    logger.info("初级会员没有折扣");
    return booksPrice;
  }

  @Override
  public void primaryMemberSay() {
    logger.info("我是初级会员");
  }
}
