package com.ww.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 权限实体类
 *
 * @author ww
 * @date 2020/11/12
 */
@Data
@TableName("sys_role_perms")
public class SysRolePerms implements Serializable {

  public static final String ID = "id";
  public static final String ROLE_ID = "role_id";
  public static final String PERMS_ID = "perms_id";
  private static final long serialVersionUID = 1L;
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
