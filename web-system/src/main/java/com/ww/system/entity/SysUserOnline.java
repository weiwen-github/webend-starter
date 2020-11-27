package com.ww.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;

/**
 * 在线用户实体类
 *
 * @author ww
 * @date 2020/11/16
 */
@Data
@TableName("sys_user_online")
public class SysUserOnline {
  public static final String SESSION_ID = "session_id";
  public static final String DEPT_NAME = "dept_name";
  public static final String LOGIN_NAME = "login_name";
  public static final String IP_ADDR = "ip_addr";
  public static final String LOGIN_LOCATION = "login_location";
  public static final String BROWSER = "browser";
  public static final String OS = "os";
  public static final String START_TIMESTAMP = "start_timestamp";
  public static final String LAST_ACCESS_TIME = "last_access_time";
  public static final String EXPIRE_TIME = "expire_time";
  private static final long serialVersionUID = 1L;
  public static HashMap<String, String> fieldAlias =
      new HashMap<String, String>() {
        {
          put("sessionId", "用户会话id");
          put("deptName", "部门名称");
          put("loginName", "登录名");
          put("ipAddr", "登录IP地址");
          put("loginLocation", "登陆地址");
          put("browser", "浏览器类型");
          put("os", "操作系统");
          put("startTimestamp", "session创建时间");
          put("lastAccessTime", "session最后访问时间");
          put("expireTime", "超时时间，单位为分钟");
        }
      };

  /** {name:"用户会话id"} */
  private String sessionId;

  /** {name:""部门名称} */
  private String deptName;

  /** {name:"登录名称"} */
  private String loginName;

  /** {name:"登录IP地址"} */
  private String ipAddr;

  /** {name:"登录地址"} */
  private String loginLocation;

  /** {name:"浏览器类型"} */
  private String browser;

  /** {name:"操作系统"} */
  private String os;

  /** {name:"session创建时间"} */
  private Date startTimestamp;

  /** {name:"session最后访问时间"} */
  private Date lastAccessTime;

  /** {name:"超时时间，单位为分钟"} */
  private Long expireTime;
}
