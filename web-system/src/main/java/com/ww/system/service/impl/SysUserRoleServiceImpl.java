package com.ww.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ww.common.utils.CommonUtils;
import com.ww.system.dao.SysUserRoleMapper;
import com.ww.system.entity.SysUserRole;
import com.ww.system.service.ISysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ww
 * @date 2020/11/12
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole>
    implements ISysUserRoleService {
  /**
   * 根据userId获取roleIds
   *
   * @param userId
   * @return java.util.Set<java.lang.Long>
   */
  @Override
  public Set<Long> getRoleIdsByUserId(Long userId) {
    List<SysUserRole> userRoles =
        this.list(new QueryWrapper<SysUserRole>().eq(SysUserRole.USER_ID, userId));
    if (CommonUtils.isNullOrEmpty(userRoles)) {
      return new HashSet<>();
    }
    return userRoles.parallelStream().map(SysUserRole::getRoleId).collect(Collectors.toSet());
  }

  /**
   * 构造userId ~ roleIds Map
   *
   * @return java.util.Map<java.lang.Long,java.util.Set<java.lang.Long>>
   */
  @Override
  public Map<Long, Set<Long>> buildUserIdRoleIdsMap() {
    List<SysUserRole> userRoles = this.list();
    Map<Long, List<SysUserRole>> map =
        userRoles.stream().collect(Collectors.groupingBy(SysUserRole::getUserId));
    Map<Long, Set<Long>> resultMap = new HashMap<>();
    map.forEach(
        (userId, userRoleList) -> {
          Set<Long> roleIds =
              userRoleList.stream().map(SysUserRole::getRoleId).collect(Collectors.toSet());
          resultMap.put(userId, roleIds);
        });
    return resultMap;
  }
}
