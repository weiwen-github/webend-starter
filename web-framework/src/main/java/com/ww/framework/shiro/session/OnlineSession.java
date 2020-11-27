package com.ww.framework.shiro.session;

import com.ww.system.constant.SystemConst;
import org.apache.shiro.session.mgt.SimpleSession;

/**
 * 在线用户会话属性
 *
 * @author ruoyi
 */
public class OnlineSession extends SimpleSession {

  private static final long serialVersionUID = 1L;

  /** 用户ID */
  private Long userId;

  /** 用户名称 */
  private String loginName;

  /** 部门名称 */
  private String deptName;

  /** 用户联系地址 */
  private String userAddress;

  /** 登录IP地址 */
  private String host;

  /** 浏览器类型 */
  private String browser;

  /** 操作系统 */
  private String os;

  /** 在线状态 */
  private String status = SystemConst.OnlineStatus.on_line.name();

  /** 属性是否改变 优化session数据同步 */
  private transient boolean attributeChanged = false;

  @Override
  public String getHost() {
    return host;
  }

  @Override
  public void setHost(String host) {
    this.host = host;
  }

  public String getBrowser() {
    return browser;
  }

  public void setBrowser(String browser) {
    this.browser = browser;
  }

  public String getOs() {
    return os;
  }

  public void setOs(String os) {
    this.os = os;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getDeptName() {
    return deptName;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void markAttributeChanged() {
    this.attributeChanged = true;
  }

  public void resetAttributeChanged() {
    this.attributeChanged = false;
  }

  public boolean isAttributeChanged() {
    return attributeChanged;
  }

  public String getUserAddress() {
    return userAddress;
  }

  public void setUserAddress(String userAddress) {
    this.userAddress = userAddress;
  }

  @Override
  public void setAttribute(Object key, Object value) {
    super.setAttribute(key, value);
  }

  @Override
  public Object removeAttribute(Object key) {
    return super.removeAttribute(key);
  }
}
