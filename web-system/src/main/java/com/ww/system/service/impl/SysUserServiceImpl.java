package com.ww.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ww.common.dto.request.PageDto;
import com.ww.common.utils.CommonUtils;
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
import java.util.Map;
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
   * 分页获取用户信息
   *
   * @param pageDto
   * @param pages
   * @param ew
   * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.ww.system.entity.SysUser>
   */
  @Override
  public Page<SysUser> listPageWithParams(PageDto pageDto, Page<SysUser> pages, QueryWrapper<SysUser> ew) {
    if (null == ew) {
      ew = new QueryWrapper<>();
    }
    ew.orderByDesc(SysUser.GMT_CREATE);
    page(pages, ew);
    return pages;
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
    int count =
        this.count(
            new QueryWrapper<SysUser>()
                .eq(SysUser.USER_NAME, user.getUserName())
                .or()
                .eq(SysUser.USER_REAL_NAME, user.getUserRealName()));
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

  /**
   * 编辑用户
   *
   * @param user
   * @return java.lang.Boolean
   */
  @Override
  public Boolean updateUser(SysUser user) {
    return this.updateById(user);
  }

  /**
   * 获取详情
   *
   * @param id
   * @return com.ww.system.entity.SysUser
   */
  @Override
  public SysUser getUserById(Long id) {
    SysUser user = this.getById(id);
    Set<Long> roleIds = sysUserRoleService.getRoleIdsByUserId(user.getUserId());
    user.setRoleIdSet(roleIds);
    if (!CommonUtils.isNullOrEmpty(roleIds)) {
      List<SysRole> roles = sysRoleService.listByIds(roleIds);
      List<String> roleSignList = roles.stream().map(SysRole::getRoleSign).collect(Collectors.toList());
      user.setRoleSignList(roleSignList);
    }
    return user;
  }

  /**
   * 填充部分字段
   *
   * @param userList
   * @return void
   */
  @Override
  public void fillField(List<SysUser> userList) {
    Map<Long, Set<Long>> idRoleIdsMap = sysUserRoleService.buildUserIdRoleIdsMap();
    userList.forEach(user -> {
      Set<Long> roleIds = idRoleIdsMap.get(user.getUserId());
      if (CommonUtils.isNullOrEmpty(roleIds)) {
        return;
      }
      List<SysRole> sysRoles = sysRoleService.getSysRoles(roleIds);
      List<String> roleSignList = sysRoles.stream().map(SysRole::getRoleSign).collect(Collectors.toList());
      user.setRoleIdSet(roleIds);
      user.setRoleSignList(roleSignList);
    });
  }
}
