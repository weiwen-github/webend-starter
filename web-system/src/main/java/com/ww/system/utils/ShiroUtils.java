package com.ww.system.utils;

import com.ww.system.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author weiwen
 * @date 2020/11/12
 */
public class ShiroUtils {

  public static Subject getSubject() {
    return SecurityUtils.getSubject();
  }

  public static Session getSession() {
    return SecurityUtils.getSubject().getSession();
  }

  public static SysUser getUserEntity() {
    return (SysUser) SecurityUtils.getSubject().getPrincipal();
  }

  public static Long getUserId() {
    if (getUserEntity() == null) {
      return null;
    }
    return getUserEntity().getUserId();
  }

  public static String getIp() {
    return getSession().getHost();
  }
}
