package com.ww.framework.shiro.service;

import com.ww.framework.shiro.session.OnlineSession;
import com.ww.system.entity.SysUserOnline;
import com.ww.system.service.ISysUserOnlineService;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 会话db操作处理
 *
 * @author ruoyi
 */
@Component
public class SysShiroService {
  @Autowired private ISysUserOnlineService sysUserOnlineService;

  /**
   * 删除会话
   *
   * @param onlineSession 会话信息
   */
  public void deleteSession(OnlineSession onlineSession) {
    sysUserOnlineService.deleteOnlineById(String.valueOf(onlineSession.getId()));
  }

  /**
   * 获取会话信息
   *
   * @param sessionId
   * @return
   */
  public Session getSession(Serializable sessionId) {
    SysUserOnline userOnline = sysUserOnlineService.selectOnlineById(String.valueOf(sessionId));
    return userOnline == null ? null : createSession(userOnline);
  }

  public Session createSession(SysUserOnline userOnline) {
    OnlineSession onlineSession = new OnlineSession();
    if (userOnline != null) {
      onlineSession.setId(userOnline.getSessionId());
      onlineSession.setHost(userOnline.getIpAddr());
      onlineSession.setBrowser(userOnline.getBrowser());
      onlineSession.setOs(userOnline.getOs());
      onlineSession.setDeptName(userOnline.getDeptName());
      onlineSession.setLoginName(userOnline.getLoginName());
      onlineSession.setStartTimestamp(userOnline.getStartTimestamp());
      onlineSession.setLastAccessTime(userOnline.getLastAccessTime());
      onlineSession.setTimeout(userOnline.getExpireTime());
    }
    return onlineSession;
  }
}
