package com.ww.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ww.system.entity.SysRole;

import java.util.List;

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
}
