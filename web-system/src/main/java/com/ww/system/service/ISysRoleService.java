package com.ww.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ww.system.entity.SysRole;

import java.util.List;
import java.util.Set;

/**
 * @author ww
 * @date 2020/11/12
 */
public interface ISysRoleService extends IService<SysRole> {
  /**
   * 获取用户角色
   *
   * @param userId
   * @return java.util.Set<java.lang.String>
   */
  List<SysRole> getSysRoles(Long userId);

  /**
   * 获取角色列表
   *
   * @param roleIds
   * @return java.util.List<com.ww.system.entity.SysRole>
   */
  List<SysRole> getSysRoles(Set<Long> roleIds);
}
