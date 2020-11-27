package com.ww.framework.shiro.manager;

import com.ww.framework.shiro.session.OnlineSession;
import com.ww.system.entity.SysUserOnline;
import com.ww.system.service.ISysUserOnlineService;
import com.ww.system.utils.SpringContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 *
 * @author liuhulu
 */
public class AsyncFactory {

  private static final Logger logger = LoggerFactory.getLogger("sys-user");

  /**
   * 同步session到数据库
   *
   * @param session 在线用户会话
   * @return 任务task
   */
  public static TimerTask syncSessionToDb(final OnlineSession session) {
    return new TimerTask() {
      @Override
      public void run() {
        SysUserOnline online = new SysUserOnline();
        online.setSessionId(String.valueOf(session.getId()));
        online.setDeptName(session.getDeptName());
        online.setLoginName(session.getLoginName());
        online.setStartTimestamp(session.getStartTimestamp());
        online.setLastAccessTime(session.getLastAccessTime());
        online.setExpireTime(session.getTimeout());
        online.setIpAddr(session.getHost());
        online.setLoginLocation(session.getHost());
        online.setBrowser(session.getBrowser());
        online.setOs(session.getOs());
        SpringContextUtils.getBean(ISysUserOnlineService.class).saveOnline(online);
      }
    };
  }
}
