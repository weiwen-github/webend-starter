package com.ww.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ww.common.dto.request.PageDto;
import com.ww.system.entity.SysUser;

import java.util.List;
import java.util.Set;

/**
 * @author ww
 * @date 2020/11/12
 */
public interface ISysUserService extends IService<SysUser> {

  /**
   * 分页获取用户信息
   *
   * @param pageDto
   * @param pages
   * @param ew
   * @return
   *     com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.ww.system.entity.SysUser>
   */
  Page<SysUser> listPageWithParams(PageDto pageDto, Page<SysUser> pages, QueryWrapper<SysUser> ew);

  /**
   * 获取用户信息
   *
   * @param username
   * @return com.ww.system.entity.SysUser
   */
  SysUser getByUserName(String username);

  /**
   * 获取用户角色标识
   *
   * @param userId
   * @return java.util.Set<java.lang.String>
   */
  Set<String> getUserRoleSigns(Long userId);

  /**
   * 获取用户授权标识
   *
   * @param userId
   * @return java.util.Set<java.lang.String>
   */
  Set<String> getUserPerms(Long userId);

  /**
   * 新增用户
   *
   * @param user
   * @return java.lang.Boolean
   */
  Boolean saveUser(SysUser user);

  /**
   * 编辑用户
   *
   * @param user
   * @return boolean
   */
  Boolean updateUser(SysUser user);

  /**
   * 获取详情
   *
   * @param id
   * @return com.ww.system.entity.SysUser
   */
  SysUser detail(Long id);

  /**
   * 填充部分字段
   *
   * @param userList
   * @return void
   */
  void fillField(List<SysUser> userList);
}
