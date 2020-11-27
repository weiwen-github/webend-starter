package com.ww.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ww.system.entity.SysUserRole;

import java.util.Map;
import java.util.Set;

/**
 * @author ww
 * @date 2020/11/12
 */
public interface ISysUserRoleService extends IService<SysUserRole> {
  /**
   * 根据userId获取roleIds
   *
   * @param userId
   * @return java.util.Set<java.lang.Long>
   */
  Set<Long> getRoleIdsByUserId(Long userId);

  /**
   * 构造userId ~ roleIds Map
   *
   * @return java.util.Map<java.lang.Long,java.util.Set<java.lang.Long>>
   */
  Map<Long, Set<Long>> buildUserIdRoleIdsMap();
}
