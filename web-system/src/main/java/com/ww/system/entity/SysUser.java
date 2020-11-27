package com.ww.system.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 用户实体类
 *
 * @author ww
 * @date 2020/11/11
 */
@Data
@TableName("sys_user")
public class SysUser implements Serializable {
  public static final String USER_ID = "user_id";
  public static final String USER_NAME = "user_name";
  public static final String PASSWORD = "password";
  public static final String USER_REAL_NAME = "user_real_name";
  public static final String DELETE_FLAG = "delete_flag";
  public static final String GMT_CREATE = "gmt_create";
  public static final String GMT_MODIFIED = "gmt_modified";
  private static final long serialVersionUID = 1L;
  public static HashMap<String, String> fieldAlias =
      new HashMap<String, String>() {
        {
          put("userId", "用户ID，主键ID");
          put("userName", "用户名");
          put("password", "密码");
          put("userRealName", "真实姓名");
          put("deleteFlag", " 是否删除,desc：0--有效、1--已删除");
          put("gmtCreate", "创建时间");
          put("gmtModified", "修改时间");
        }
      };
  /** {name:"用户ID,主键"} */
  @TableId(value = "user_id", type = IdType.AUTO)
  private Long userId;

  /** {name:"用户名"} */
  private String userName;

  /** {name:"密码"} */
  private String password;

  /** {name:"用户真实姓名"} */
  private String userRealName;

  /** 创建用户id */
  private Long createUserId;

  /** {name:" 是否删除",desc："0--有效、1--已删除"} */
  @TableLogic private Integer deleteFlag;

  /** {name:"添加时间"} */
  private LocalDateTime gmtCreate;

  /** {name:"修改时间"} */
  private LocalDateTime gmtModified;

  @TableField(exist = false)
  @JSONField(serialize = false)
  private Set<Long> roleIdSet;

  @TableField(exist = false)
  @JSONField(serialize = false)
  private List<String> roleSignList;
}
