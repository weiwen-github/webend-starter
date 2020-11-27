package com.ww.business.test.strategypattern.service;

/**
 * 具体策略类 : 中级会员折扣类
 *
 * @author weiwen
 * @date 2020/11/17
 */
public interface IIntermediateMemberStrategyService extends MemberStrategy {
  /** 中级会员自己的方法 */
  void intermediateMemberSay();
}
