package com.ww.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ww.system.entity.SysUser;

import java.util.Set;

/**
 * @author ww
 * @date 2020/11/12
 */
public interface ISysUserService extends IService<SysUser> {
  /**
   * 登录
   *
   * @param username
   * @param password
   * @return java.lang.Boolean
   */
  Boolean login(String username, String password);

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
}
