package com.ww.business.test.strategypattern.strateenum;

/**
 *
 * @author ww
 * @date 2020/11/18
 */
public enum MemberEnum {
  /** 会员 */
  SENIOR_MEMBER("高级会员"),
  INTERMEDIATE_MEMBER("中级会员"),
  PRIMARY_MEMBER("初级会员");

  private String member;

  MemberEnum(String member) {
    this.member = member;
  }
}
