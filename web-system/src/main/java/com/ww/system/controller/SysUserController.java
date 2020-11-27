package com.ww.system.controller;

import com.ww.common.dto.response.RespBody;
import com.ww.system.entity.SysUser;
import com.ww.system.service.ISysPermsService;
import com.ww.system.service.ISysUserService;
import com.ww.system.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ww
 * @date 2020/11/12
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class SysUserController {

  @Autowired ISysUserService sysUserService;

  @Autowired ISysPermsService sysPermsService;

  @ResponseBody
  @PostMapping("/login")
  public RespBody login(String username, String password) {
    try {
      log.info("登录：账号：{}， 密码：{}", username, password);
      Subject subject = ShiroUtils.getSubject();
      UsernamePasswordToken token = new UsernamePasswordToken(username, password);
      subject.login(token);
    } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
      return RespBody.error(e.getMessage());
    } catch (AuthenticationException e) {
      return RespBody.error("验证失败");
    }
    return RespBody.ok("验证成功");
  }

  /**
   * 新增用户
   *
   * @param user
   * @return com.ww.common.dto.response.RespBody
   */
  @PostMapping("/save")
  public RespBody save(@RequestBody SysUser user) {
    Boolean add = sysUserService.saveUser(user);
    if (add) {
      return RespBody.ok("添加成功");
    } else {
      return RespBody.error("添加失败");
    }
  }
}
