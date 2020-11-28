package com.ww.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 用户角色实体类
 *
 * @author ww
 * @date 2020/11/14
 */
@Data
@TableName("sys_user_role")
public class SysUserRole implements Serializable {
  public static final String ID = "";
  public static final String USER_ID = "user_id";
  public static final String ROLE_ID = "role_id";

  public static HashMap<String, String> fieldAlias =
      new HashMap<String, String>() {
        {
          put("id", "主键");
          put("userId", "用户ID");
          put("roleId", "角色ID");
        }
      };
  /** 主键 */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /** 用户ID */
  private Long userId;

  /** 角色ID */
  private Long roleId;
}
