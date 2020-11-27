package com.ww.business.test.strategypattern.service;

/**
 * 具体策略类 : 初级会员折扣类
 *
 * @author ww
 * @date 2020/11/17
 */
public interface IPrimaryMemberStrategyService extends MemberStrategy {
  /** 初级会员自己方法 */
  void primaryMemberSay();
}
