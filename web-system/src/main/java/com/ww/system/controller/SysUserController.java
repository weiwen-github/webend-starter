package com.ww.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ww.common.constant.ResponseConst;
import com.ww.common.controller.BaseController;
import com.ww.common.dto.request.IdDto;
import com.ww.common.dto.request.PageDto;
import com.ww.common.dto.response.PageResp;
import com.ww.common.dto.response.RespBody;
import com.ww.common.utils.CommonUtils;
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
public class SysUserController extends BaseController {

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

  @PostMapping("/list")
  public PageResp list(@RequestBody PageDto pageDto) {
    log.info("分页参数：{}", pageDto);
    Page<SysUser> pages = getPage(pageDto);
    pages = sysUserService.listPageWithParams(pageDto, pages, null);
    // 填充部分字段
    sysUserService.fillField(pages.getRecords());
    return getPageResFromPage(pages);
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
      return RespBody.ok(ResponseConst.ADD_OK);
    } else {
      return RespBody.error(ResponseConst.ADD_ERROR);
    }
  }

  /**
   * 获取用户详情
   *
   * @param idDto
   * @return com.ww.common.dto.response.RespBody
   */
  @PostMapping("/detail")
  public RespBody<SysUser> detail(@RequestBody IdDto idDto) {
    Long id = idDto.getId();
    if (CommonUtils.isNullOrEmpty(id)) {
      return RespBody.error("请输入ID");
    }
    SysUser user = sysUserService.detail(id);
    return RespBody.ok().data(user);
  }

  /**
   * 编辑用户
   *
   * @param user
   * @return com.ww.common.dto.response.RespBody
   */
  @PostMapping("/update")
  public RespBody update(@RequestBody SysUser user) {
    boolean update = sysUserService.updateUser(user);
    if (update) {
      return RespBody.ok(ResponseConst.UPDATE_OK);
    } else {
      return RespBody.error(ResponseConst.UPDATE_ERROR);
    }
  }

  /**
   * 删除用户
   *
   * @param idDto
   * @return com.ww.common.dto.response.RespBody
   */
  @PostMapping("/remove")
  public RespBody remove(@RequestBody IdDto idDto) {
    Long id = idDto.getId();
    if (CommonUtils.isNullOrEmpty(id)) {
      return RespBody.error("请输入ID");
    }
    boolean remove = sysUserService.removeById(id);
    if (remove) {
      return RespBody.ok(ResponseConst.REMOVE_OK);
    } else {
      return RespBody.error(ResponseConst.REMOVE_ERROR);
    }
  }
}
