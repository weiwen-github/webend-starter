package com.ww.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

/**
 * @author weiwen
 * @date 2020/11/12
 */
@Data
@TableName("sys_role_perms")
public class SysRolePerms implements Serializable {

  private static final long serialVersionUID = 1L;

  public static final String ID = "id";
  public static final String ROLE_ID = "role_id";
  public static final String PERMS_ID = "perms_id";

  public static HashMap<String, String> fieldAlias =
          new HashMap<String, String>() {
            {
              put("id", "主键");
              put("roleId", "角色id");
              put("permsId", "权限id");
            }
          };
  /** 主键 */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /** {name:"角色id"} */
  private Long roleId;

  /** {name:"角色名称"} */
  private Long permsId;

}
