package com.ww.system.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ww.system.constant.DateConst;
import com.ww.system.dao.SysUserOnlineMapper;
import com.ww.system.entity.SysUserOnline;
import com.ww.system.service.ISysUserOnlineService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author weiwen
 * @date 2020/11/12
 */
@Service
public class SysUserOnlineServiceImpl extends ServiceImpl<SysUserOnlineMapper, SysUserOnline>
    implements ISysUserOnlineService {
  @Override
  public void saveOnline(SysUserOnline online) {
    this.save(online);
  }

  /**
   * 通过sesionId删除信息
   *
   * @param sessionId 会话ID
   * @return 在线用户信息
   */
  @Override
  public void deleteOnlineById(String sessionId) {
    SysUserOnline userOnline =
        this.getOne(new QueryWrapper<SysUserOnline>().eq(SysUserOnline.SESSION_ID, sessionId));
    if (null != userOnline) {
      this.removeById(sessionId);
    }
  }

  /**
   * 查询会话集合
   *
   * @param expiredDate 有效期
   * @return 会话集合
   */
  @Override
  public List<SysUserOnline> selectOnlineByExpired(Date expiredDate) {
    String lastAccessTime = DateUtil.format(expiredDate, DateConst.FORMAT_DATETIME);
    return this.list(
        new QueryWrapper<SysUserOnline>()
            .eq(SysUserOnline.LAST_ACCESS_TIME, lastAccessTime)
            .orderByAsc(SysUserOnline.LAST_ACCESS_TIME));
  }

  /**
   * 通过会话序号删除信息
   *
   * @param sessions 会话ID集合
   * @return 在线用户信息
   */
  @Override
  public void deleteBatchOnline(List<String> sessions) {
    for (String sessionId : sessions) {
      SysUserOnline userOnline = this.getById(sessionId);
      if (userOnline != null) {
        this.removeById(sessionId);
      }
    }
  }

  /**
   * 通过会话序号查询信息
   *
   * @param sessionId 会话ID
   * @return 在线用户信息
   */
  @Override
  public SysUserOnline selectOnlineById(String sessionId) {
    return this.getById(sessionId);
  }
}
