package com.ww.system.controller;

import com.ww.common.dto.response.RespBody;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ww
 */
@RestController
@RequestMapping("/api/v1/sysAuth")
public class SysAuthController {

  /** filter 过滤如果没有访问权限则定位到该接口 */
  @ApiOperation("无权限验证")
  @RequestMapping("/noAuth")
  public RespBody error() {
    return RespBody.error(10000 , "没有访问权限");
  }
}
