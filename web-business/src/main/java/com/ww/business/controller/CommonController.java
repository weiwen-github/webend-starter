package com.ww.business.controller;

import com.ww.business.common.BaseController;
import com.ww.business.controller.dto.ReqDto;
import com.ww.business.test.strategypattern.context.MemberContext;
import com.ww.business.test.strategypattern.service.MemberStrategy;
import com.ww.business.test.strategypattern.strateenum.MemberEnum;
import com.ww.common.dto.response.RespBody;
import com.ww.system.entity.SysUser;
import com.ww.system.utils.AESUtils;
import com.ww.system.utils.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author ww
 * @date 2020/11/10
 */
@Api(tags = "api-common")
@RestController
@RequestMapping("/common")
public class CommonController extends BaseController {

  @ApiOperation(value = "测试1")
  @PostMapping("/list")
  @RequiresPermissions(value = {"admin:list"})
  public RespBody string(@RequestBody ReqDto reqDto) {
    SysUser user = ShiroUtils.getUserEntity();
    logger.info("在线用户：{}", user);
    logger.info("请求参数：{}", reqDto.getDateTime());
    MemberStrategy strategy = MemberContext.getMemberStrategy(MemberEnum.SENIOR_MEMBER);
    logger.info("策略对象：{}", strategy.countPrice(300));

    MemberStrategy strategy1 = MemberContext.getMemberStrategy(MemberEnum.INTERMEDIATE_MEMBER);
    logger.info("策略对象:{}", strategy1.countPrice(300));

    MemberStrategy strategy2 = MemberContext.getMemberStrategy(MemberEnum.PRIMARY_MEMBER);
    logger.info("策略对象：{}", strategy2.countPrice(300));

    return RespBody.ok().data(reqDto.getDateTime());
  }

  @ApiOperation("加密解密测试")
  @PostMapping("/encrypt")
  public RespBody encrypt() {
    SysUser user = ShiroUtils.getUserEntity();
    logger.info("在线用户：{}", user);
    String encrypt = "";
    try{
      encrypt = AESUtils.encrypt(user.getPassword());
      logger.info("加密加密：{}", encrypt);
    } catch (Exception e) {
      logger.info("加密失败：{}", e.getMessage());
    }
    String decrypt = "";
    try{
      decrypt = AESUtils.decrypt(encrypt);
      logger.info("加密解密：{}", decrypt);
    } catch (Exception e) {
      logger.info("解密失败：{}", e.getMessage());
    }

    return RespBody.ok().data(decrypt);
  }
}
