package com.ww.framework.shiro.reaml;

import com.ww.common.utils.CommonUtils;
import com.ww.system.entity.SysRole;
import com.ww.system.entity.SysUser;
import com.ww.system.service.ISysPermsService;
import com.ww.system.service.ISysRoleService;
import com.ww.system.service.ISysUserRoleService;
import com.ww.system.service.ISysUserService;
import com.ww.system.utils.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ww
 * @date 2020/11/12
 */
public class UserRealm extends AuthorizingRealm {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired ISysUserService sysUserService;
  @Autowired ISysRoleService sysRoleService;
  @Autowired
  ISysUserRoleService sysUserRoleService;
  @Autowired ISysPermsService sysPermsService;

  /**
   * 验证
   *
   * @param token
   * @return org.apache.shiro.authc.AuthenticationInfo
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
      throws AuthenticationException {
    String username = (String) token.getPrincipal();
    String password = new String((char[]) token.getCredentials());
    logger.info("验证：账号：{}， 密码：{}", username, password);

    // 查用户信息
    SysUser user = sysUserService.getByUserName(username);
    logger.info("验证用户：{}", user);
    if (null == user) {
      throw new UnknownAccountException("账号或密码不正确");
    }
    if (!password.equals(user.getPassword())) {
      throw new IncorrectCredentialsException("账号或密码不正确");
    }
    user.setRoleIdSet(sysUserRoleService.getRoleIdsByUserId(user.getUserId()));
    logger.info("用户角色ID:{}", user.getRoleIdSet());
    if (!CommonUtils.isNullOrEmpty(user.getRoleIdSet())) {
      List<SysRole> roles = sysRoleService.listByIds(user.getRoleIdSet());
      List<String> roleSignList = roles.stream().map(SysRole::getRoleSign).collect(Collectors.toList());
      user.setRoleSignList(roleSignList);
    }
    SimpleAuthenticationInfo info =
        new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    return info;
  }

  /**
   * 授权
   *
   * @param principalCollection
   * @return org.apache.shiro.authz.AuthorizationInfo
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    logger.info("授权：执行了 doGetAuthorizationInfo 方法");
    // 只有使用了 @RequiresPermissions 注解，才有调用此方法
    // 获取用户名
    Long userId = ShiroUtils.getUserId();
    // 用户角色标识
    Set<String> roleSignSet = sysUserService.getUserRoleSigns(userId);
    logger.info("用户角色标识：{}", roleSignSet);
    // 用户权限标识
    Set<String> permsSet = sysUserService.getUserPerms(userId);
    logger.info("角色权限标识：{}", permsSet);
    // 添加角色和权限
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    info.setRoles(roleSignSet);
    info.setStringPermissions(permsSet);

    return info;
  }
}
