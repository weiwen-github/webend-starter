package com.ww.system.constant;

/**
 * @author weiwen
 * @date 2020/11/12
 */
public class SystemConst {

  public enum OnlineStatus {
    /** 用户状态 */
    on_line("在线"),
    off_line("离线");

    private final String info;

    OnlineStatus(String info) {
      this.info = info;
    }

    public String getInfo() {
      return info;
    }
  }
}
