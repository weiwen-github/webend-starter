package com.ww.business.test.strategypattern.context;

import com.ww.business.test.strategypattern.service.IIntermediateMemberStrategyService;
import com.ww.business.test.strategypattern.service.IPrimaryMemberStrategyService;
import com.ww.business.test.strategypattern.service.ISeniorMemberStrategyService;
import com.ww.business.test.strategypattern.service.MemberStrategy;
import com.ww.business.test.strategypattern.strateenum.MemberEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 环境角色（容器）类
 *
 * @author ww
 * @date 2020/11/17
 */
@Service
public class MemberContext {

  private static final Map<MemberEnum, MemberStrategy> memberStrategyMap =
      new ConcurrentHashMap<>();
  /** 持有具体策略对象 */
  @Autowired
  @Qualifier("seniorMemberStrategyServiceImpl")
  private ISeniorMemberStrategyService seniorMemberStrategyServiceImpl;

  @Autowired
  @Qualifier("intermediateMemberStrategyServiceImpl")
  private IIntermediateMemberStrategyService intermediateMemberStrategyServiceImpl;

  @Autowired
  @Qualifier("primaryMemberStrategyServiceImpl")
  private IPrimaryMemberStrategyService primaryMemberStrategyServiceImpl;

  /**
   * 获取具体的策略对象
   *
   * @param memberEnum
   * @return com.ww.business.test.strategypattern.service.MemberStrategy
   */
  public static MemberStrategy getMemberStrategy(MemberEnum memberEnum) {
    return memberStrategyMap.get(memberEnum);
  }

  /** 具体策略对象 */
  @PostConstruct
  public void init() {
    memberStrategyMap.put(MemberEnum.SENIOR_MEMBER, seniorMemberStrategyServiceImpl);
    memberStrategyMap.put(MemberEnum.INTERMEDIATE_MEMBER, intermediateMemberStrategyServiceImpl);
    memberStrategyMap.put(MemberEnum.PRIMARY_MEMBER, primaryMemberStrategyServiceImpl);
  }


}
