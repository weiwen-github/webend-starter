package com.ww.business.test.strategypattern.service;

/**
 * 高级会员抽象策略类
 *
 * @author ww
 * @date 2020/11/17
 */
public interface ISeniorMemberStrategyService extends MemberStrategy {
  /** 高级会员自己的方法 */
  void advancedMemberSay();
}
