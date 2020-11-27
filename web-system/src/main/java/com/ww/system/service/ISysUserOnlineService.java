package com.ww.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ww.system.entity.SysUser;
import com.ww.system.entity.SysUserOnline;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author ww
 * @date 2020/11/12
 */
public interface ISysUserOnlineService extends IService<SysUserOnline> {
  /**
   * 保存会话信息
   *
   * @param online 会话信息
   */
  void saveOnline(SysUserOnline online);

  /**
   * 通过sesionId删除信息
   *
   * @param sessionId 会话ID
   * @return 在线用户信息
   */
  void deleteOnlineById(String sessionId);

  /**
   * 查询会话集合
   *
   * @param expiredDate 有效期
   * @return 会话集合
   */
  List<SysUserOnline> selectOnlineByExpired(Date expiredDate);

  /**
   * 通过会话序号删除信息
   *
   * @param sessions 会话ID集合
   * @return 在线用户信息
   */
  void deleteBatchOnline(List<String> sessions);

  /**
   * 通过会话序号查询信息
   *
   * @param sessionId 会话ID
   * @return 在线用户信息
   */
  SysUserOnline selectOnlineById(String sessionId);
}
