package com.ww.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ww.system.entity.SysPermission;

import java.util.List;

/**
 * @author ww
 * @date 2020/11/12
 */
public interface ISysPermsService extends IService<SysPermission> {
  /**
   * 获取用户权限
   *
   * @param userId
   * @return java.util.Set<java.lang.String>
   */
  List<SysPermission> getSysPermission(Long userId);
}
