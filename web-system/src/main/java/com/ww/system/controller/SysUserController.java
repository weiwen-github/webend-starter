package com.ww.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ww.common.constant.ResponseConst;
import com.ww.common.controller.BaseController;
import com.ww.common.dto.request.IdDto;
import com.ww.common.dto.request.IdsDto;
import com.ww.common.dto.request.PageDto;
import com.ww.common.dto.response.PageResp;
import com.ww.common.dto.response.RespBody;
import com.ww.common.utils.CommonUtils;
import com.ww.system.entity.SysUser;
import com.ww.system.service.ISysPermsService;
import com.ww.system.service.ISysUserService;
import com.ww.system.utils.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ww
 * @date 2020/11/12
 */
@Api(tags = "SysUser")
@RestController
@RequestMapping("/api/v1/user")
public class SysUserController extends BaseController {

  @Autowired ISysUserService sysUserService;

  @Autowired ISysPermsService sysPermsService;

  @ApiOperation("登录")
  @PostMapping("/login")
  public RespBody login(String username, String password) {
    try {
      logger.info("登录账号：{}， 密码：{}", username, password);
      Subject subject = ShiroUtils.getSubject();
      UsernamePasswordToken token = new UsernamePasswordToken(username, password);
      subject.login(token);
    } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
      return RespBody.error(e.getMessage());
    } catch (AuthenticationException e) {
      return RespBody.error("登录失败");
    }
    return RespBody.ok("登录成功");
  }

  @ApiOperation("用户列表")
  @PostMapping("/list")
  public PageResp list(@RequestBody PageDto pageDto) {
    logger.info("分页参数：{}", pageDto);
    Page<SysUser> pages = getPage(pageDto);
    pages = sysUserService.listPageWithParams(pageDto, pages, null);
    // 填充部分字段
    sysUserService.fillField(pages.getRecords());
    return getPageResultFromPage(pages);
  }

  /**
   * 新增用户
   *
   * @param user
   * @return com.ww.common.dto.response.RespBody
   */
  @ApiOperation("新增用户")
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
  @ApiOperation("用户详情")
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
  @ApiOperation("编辑用户")
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
  @ApiOperation("删除用户")
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

  /**
   * 删除用户
   *
   * @param idsDto
   * @return com.ww.common.dto.response.RespBody
   */
  @ApiOperation("删除某些用户")
  @PostMapping("/removeAny")
  public RespBody removeAny(@RequestBody IdsDto idsDto) {
    List<Long> ids = idsDto.getIds();
    if (CommonUtils.isNullOrEmpty(ids)) {
      return RespBody.error("请输入ID");
    }
    boolean remove = sysUserService.removeByIds(ids);
    if (remove) {
      return RespBody.ok(ResponseConst.REMOVE_OK);
    } else {
      return RespBody.error(ResponseConst.REMOVE_ERROR);
    }
  }

  /**
   * 删除所有用户
   *
   * @return com.ww.common.dto.response.RespBody
   */
  @ApiOperation("删除所有用户")
  @PostMapping("/removeAll")
  public RespBody removeAll() {
    boolean remove = sysUserService.remove(null);
    if (remove) {
      return RespBody.ok(ResponseConst.REMOVE_OK);
    } else {
      return RespBody.error(ResponseConst.REMOVE_ERROR);
    }
  }
}
