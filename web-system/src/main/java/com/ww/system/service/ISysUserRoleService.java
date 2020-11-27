package com.ww.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ww.system.entity.SysUserRole;
import javafx.scene.effect.SepiaTone;

import java.util.Set;

/**
 *
 * @author weiwen
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
}
