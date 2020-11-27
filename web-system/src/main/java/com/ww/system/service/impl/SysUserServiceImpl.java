package com.ww.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ww.system.dao.SysUserMapper;
import com.ww.system.entity.SysPermission;
import com.ww.system.entity.SysRole;
import com.ww.system.entity.SysUser;
import com.ww.system.entity.SysUserRole;
import com.ww.system.exception.RRException;
import com.ww.system.service.*;
import com.ww.system.utils.MD5Utils;
import com.ww.system.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ww
 * @date 2020/11/12
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements ISysUserService {
  @Autowired ISysRoleService sysRoleService;
  @Autowired ISysPermsService sysPermsService;
  @Autowired ISysUserRoleService sysUserRoleService;
  @Autowired ISysRolePermsService sysRolePermsService;

  /**
   * 登录
   *
   * @param username
   * @param password
   * @return java.lang.Boolean
   */
  @Override
  public Boolean login(String username, String password) {
    SysUser sysUser =
        this.getOne(
            new QueryWrapper<SysUser>()
                .eq(SysUser.USER_NAME, username)
                .eq(SysUser.PASSWORD, password));
    return sysUser != null;
  }

  @Override
  public SysUser getByUserName(String username) {
    // 查询用户
    return this.getOne(new QueryWrapper<SysUser>().eq(SysUser.USER_NAME, username));
  }

  /**
   * 获取用户角色标识
   *
   * @param userId
   * @return java.util.Set<java.lang.String>
   */
  @Override
  public Set<String> getUserRoleSigns(Long userId) {
    // 用户角色
    List<SysRole> roles = sysRoleService.getSysRoles(userId);
    return roles.stream().map(SysRole::getRoleSign).collect(Collectors.toSet());
  }

  /**
   * 获取用户授权标识
   *
   * @param userId
   * @return java.util.Set<java.lang.String>
   */
  @Override
  public Set<String> getUserPerms(Long userId) {
    // 用户权限
    List<SysPermission> permsList = sysPermsService.getSysPermission(userId);
    return permsList.stream().map(SysPermission::getPerms).collect(Collectors.toSet());
  }

  /**
   * 新增用户
   *
   * @param user
   * @return java.lang.Boolean
   */
  @Override
  public Boolean saveUser(SysUser user) {
    // 密码加密
    user.setPassword(MD5Utils.encrypt(user.getPassword()));
    user.setGmtCreate(LocalDateTime.now());
    user.setCreateUserId(ShiroUtils.getUserId());
    int count = this.count(new QueryWrapper<SysUser>().eq(SysUser.USER_NAME, user.getUserName()).or().eq(SysUser.USER_REAL_NAME, user.getUserRealName()));
    if (count > 0) {
      throw new RRException("已存在该用户名或者用户真实姓名，请重新输入");
    }
    boolean save = this.save(user);
    if (save) {
      Long userId = user.getUserId();
      Set<Long> roleIdSet = user.getRoleIdSet();
      List<SysUserRole> userRoles = new ArrayList<>();
      roleIdSet.forEach(
          roleId -> {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoles.add(userRole);
          });
      save = sysUserRoleService.saveBatch(userRoles);
    }
    return save;
  }
}
