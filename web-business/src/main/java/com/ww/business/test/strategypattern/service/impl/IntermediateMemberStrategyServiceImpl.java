package com.ww.business.test.strategypattern.service.impl;

import com.ww.business.test.strategypattern.service.IIntermediateMemberStrategyService;
import com.ww.business.test.strategypattern.strateenum.MemberEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 具体策略类 : 中级会员折扣类
 *
 * @author ww
 * @date 2020/11/18
 */
@Service
public class IntermediateMemberStrategyServiceImpl implements IIntermediateMemberStrategyService {
  protected final Logger logger = LoggerFactory.getLogger(this.getClass());
  @Override
  public void intermediateMemberSay() {
    System.out.println("我是中级会员");
  }

  @Override
  public double countPrice(double booksPrice) {
    logger.info("中级会员折扣10%");
    return booksPrice * 0.9;
  }
}
