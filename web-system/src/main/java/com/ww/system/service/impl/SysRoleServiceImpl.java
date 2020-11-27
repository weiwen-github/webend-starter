package com.ww.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ww.common.utils.CommonUtils;
import com.ww.system.dao.SysRoleMapper;
import com.ww.system.entity.SysRole;
import com.ww.system.entity.SysUserRole;
import com.ww.system.service.ISysRoleService;
import com.ww.system.service.ISysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ww
 * @date 2020/11/12
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
    implements ISysRoleService {
  @Autowired ISysUserRoleService sysUserRoleService;

  /**
   * 获取用户角色
   *
   * @param userId
   * @return java.util.Set<java.lang.String>
   */
  @Override
  public List<SysRole> getSysRoles(Long userId) {
    List<SysUserRole> userRoles =
        sysUserRoleService.list(new QueryWrapper<SysUserRole>().eq(SysUserRole.USER_ID, userId));
    Set<Long> roleIds = userRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toSet());
    if (CommonUtils.isNullOrEmpty(roleIds)) {
      return new ArrayList<>();
    }
    // 用户角色
    return this.list(new QueryWrapper<SysRole>().in(SysRole.ROLE_ID, roleIds));
  }

  /**
   * 获取角色对象列表
   *
   * @param roleIds
   * @return java.util.List<com.ww.system.entity.SysRole>
   */
  @Override
  public List<SysRole> getSysRoles(Set<Long> roleIds) {
    List<SysRole> roles = new ArrayList<>();
    if (CommonUtils.isNullOrEmpty(roleIds)) {
      roles.addAll(this.list());
    } else {
      roles.addAll(this.listByIds(roleIds));
    }
    return roles;
  }
}
