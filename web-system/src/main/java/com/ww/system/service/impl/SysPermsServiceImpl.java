package com.ww.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ww.common.utils.CommonUtils;
import com.ww.system.dao.SysPermsMapper;
import com.ww.system.entity.SysPermission;
import com.ww.system.entity.SysRole;
import com.ww.system.entity.SysRolePerms;
import com.ww.system.service.ISysPermsService;
import com.ww.system.service.ISysRolePermsService;
import com.ww.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author weiwen
 * @date 2020/11/12
 */
@Service
public class SysPermsServiceImpl extends ServiceImpl<SysPermsMapper, SysPermission>
        implements ISysPermsService {
  @Autowired
  ISysRoleService sysRoleService;
  @Autowired
  ISysRolePermsService sysRolePermsService;

  /**
   * 获取用户权限
   *
   * @param userId
   * @return java.util.Set<java.lang.String>
   */
  @Override
  public List<SysPermission> getSysPermission(Long userId) {
    List<SysRole> userRoles = sysRoleService.getSysRoles(userId);
    Set<Long> roleIds = userRoles.stream().map(SysRole::getRoleId).collect(Collectors.toSet());
    if (CommonUtils.isNullOrEmpty(roleIds)) {
      return new ArrayList<>();
    }
    List<SysRolePerms> sysRolePerms =
            sysRolePermsService.list(
                    new QueryWrapper<SysRolePerms>().in(SysRolePerms.ROLE_ID, roleIds));
    Set<Long> permsIds =
            sysRolePerms.stream().map(SysRolePerms::getPermsId).collect(Collectors.toSet());
    if (CommonUtils.isNullOrEmpty(permsIds)) {
      return new ArrayList<>();
    }
    return this.list(new QueryWrapper<SysPermission>().in(SysPermission.ID, permsIds));
  }
}
